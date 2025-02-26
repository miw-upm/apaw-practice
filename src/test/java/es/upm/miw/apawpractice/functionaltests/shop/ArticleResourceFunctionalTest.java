package es.upm.miw.apawpractice.functionaltests.shop;

import es.upm.miw.apawpractice.adapters.rest.shop.ArticleResource;
import es.upm.miw.apawpractice.domain.models.shop.Article;
import es.upm.miw.apawpractice.domain.models.shop.ArticlePriceUpdating;
import es.upm.miw.apawpractice.functionaltests.RestTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RestTestConfig
class ArticleResourceFunctionalTest {

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
        Article article = new Article("666004", "art rest", new BigDecimal("3.00"), null);
        HttpEntity<Article> request = new HttpEntity<>(article, this.headers);
        ResponseEntity<Article> response = restTemplate.exchange(this.baseUrl, HttpMethod.POST, request, Article.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void testCreateConflict() {
        Article article =
                new Article("84001", "repeated", new BigDecimal("3.00"), null);
        HttpEntity<Article> request = new HttpEntity<>(article, this.headers);
        ResponseEntity<String> response = restTemplate.exchange(this.baseUrl, HttpMethod.POST, request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdatePrices() {
        List<ArticlePriceUpdating> articlePriceUpdatingList = Arrays.asList(
                new ArticlePriceUpdating("84001", new BigDecimal("1.23")),
                new ArticlePriceUpdating("84002", new BigDecimal("0.27"))
        );
        HttpEntity<List<ArticlePriceUpdating>> request = new HttpEntity<>(articlePriceUpdatingList, this.headers);

        ResponseEntity<Void> response = restTemplate.exchange(baseUrl, HttpMethod.PATCH, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
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
        String url = this.baseUrl + ArticleResource.SEARCH + "?q=provider:prov 1;price:1.02";
        ResponseEntity<Article[]> response = restTemplate.getForEntity(url, Article[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody()[0].getProvider()).isEqualTo("prov 1");
        assertThat(new BigDecimal("1.02")).isLessThan(response.getBody()[0].getPrice());
    }

    @Test
    void testSearchByProviderAndPriceGreaterThanBadRequest() {
        String url = baseUrl + ArticleResource.SEARCH + "?q=kk:prov 1;price:1.02";
        ResponseEntity<Void> response = restTemplate.getForEntity(url, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
