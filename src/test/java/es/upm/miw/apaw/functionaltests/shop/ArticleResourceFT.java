package es.upm.miw.apaw.functionaltests.shop;

import es.upm.miw.apaw.adapters.rest.shop.ArticleResource;
import es.upm.miw.apaw.domain.models.shop.Article;
import es.upm.miw.apaw.domain.models.shop.ArticlePriceUpdating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ArticleResourceFT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;
    private HttpHeaders headers;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + ArticleResource.ARTICLES;
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void testCreate() {
        Article article = Article.builder().barcode("666004").summary("art rest").price(new BigDecimal("3.00")).build();
        HttpEntity<Article> request = new HttpEntity<>(article, this.headers);
        ResponseEntity<Article> response = restTemplate.exchange(this.baseUrl, HttpMethod.POST, request, Article.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void testCreateBarcodeConflict() {
        Article article = Article.builder().barcode("84001").summary("repeated").price(new BigDecimal("3.00")).build();
        HttpEntity<Article> request = new HttpEntity<>(article, this.headers);
        ResponseEntity<String> response = restTemplate.exchange(this.baseUrl, HttpMethod.POST, request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testCreateBadRequest() {
        Article article = Article.builder().barcode("84001").summary("repeated").build();
        HttpEntity<Article> request = new HttpEntity<>(article, this.headers);
        ResponseEntity<String> response = restTemplate.exchange(this.baseUrl, HttpMethod.POST, request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testUpdatePrices() {
        Article article = Article.builder().barcode("666005").summary("art rest").price(new BigDecimal("3.00")).build();
        HttpEntity<Article> request = new HttpEntity<>(article, this.headers);
        restTemplate.exchange(this.baseUrl, HttpMethod.POST, request, Article.class);
        List<ArticlePriceUpdating> articlePriceUpdatingList = List.of(
                new ArticlePriceUpdating("666005", new BigDecimal("3.33"))
        );
        HttpEntity<List<ArticlePriceUpdating>> request2 = new HttpEntity<>(articlePriceUpdatingList, this.headers);

        ResponseEntity<Article> response2 = restTemplate.exchange(baseUrl, HttpMethod.PATCH, request2, Article.class);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testUpdatePricesNotFound() {
        List<ArticlePriceUpdating> articlePriceUpdatingList =
                List.of(new ArticlePriceUpdating("0", BigDecimal.ONE)
                );
        HttpEntity<List<ArticlePriceUpdating>> request = new HttpEntity<>(articlePriceUpdatingList, headers);
        ResponseEntity<Void> response = restTemplate.exchange(this.baseUrl, HttpMethod.PATCH, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testSearchByProviderAndPriceGreaterThan() {
        String url = this.baseUrl + "?provider={provider}&price={price}";
        ResponseEntity<Article[]> response = restTemplate.getForEntity(url, Article[].class, "prov 1", "1.02");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(Arrays.stream(response.getBody()).map(Article::getProvider).allMatch("prov 1"::equals)).isTrue();
        Arrays.stream(response.getBody())
                .forEach(item -> assertThat(item.getPrice()).isGreaterThan(new BigDecimal("1.02")));
    }

    @Test
    void testSearchByProviderAndPriceGreaterThanBadRequest() {
        String url = baseUrl + "?provider={provider}";
        ResponseEntity<Void> response = restTemplate.getForEntity(url, Void.class, "prov 1");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
