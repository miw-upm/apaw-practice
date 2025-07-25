package es.upm.miw.apaw.functionaltests.shop;

import es.upm.miw.apaw.adapters.rest.shop.ShoppingCartResource;
import es.upm.miw.apaw.domain.models.shop.Article;
import es.upm.miw.apaw.domain.models.shop.ArticleItem;
import es.upm.miw.apaw.domain.models.shop.ShoppingCart;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ShoppingCartEntityResourceFT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;
    private HttpHeaders headers;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + ShoppingCartResource.SHOPPING_CARTS;
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void testUpdate() {
        String url = this.baseUrl + ShoppingCartResource.ID_ID + ShoppingCartResource.ARTICLE_ITEMS;
        Article article1 = Article.builder().barcode("84002").summary("art 002").price(new BigDecimal("0.27"))
                .provider("prov 2").build();
        Article article2 = Article.builder().barcode("84003").summary("art 003").price(new BigDecimal("12.13"))
                .provider("prov 3").build();
        List<ArticleItem> articleItemArray = Arrays.asList(
                new ArticleItem(article1, 2, BigDecimal.ONE),
                new ArticleItem(article2, 3, BigDecimal.TEN)
        );

        HttpEntity<List<ArticleItem>> request = new HttpEntity<>(articleItemArray, headers);
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.PUT, request, Void.class, "kk");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testFindByPriceGreaterThan() {
        String url = this.baseUrl + "?price={price}";

        ResponseEntity<ShoppingCart[]> response = restTemplate.getForEntity(url, ShoppingCart[].class, 5.0);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        assertTrue(Arrays.stream(response.getBody())
                .anyMatch(item -> "user2" .equals(item.getUser())));
        assertTrue(response.getBody().length > 0);
    }
}