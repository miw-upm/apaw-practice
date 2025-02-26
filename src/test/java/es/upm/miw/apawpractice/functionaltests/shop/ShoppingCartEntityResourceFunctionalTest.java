package es.upm.miw.apawpractice.functionaltests.shop;

import es.upm.miw.apawpractice.adapters.rest.shop.ShoppingCartResource;
import es.upm.miw.apawpractice.domain.models.shop.Article;
import es.upm.miw.apawpractice.domain.models.shop.ArticleItem;
import es.upm.miw.apawpractice.domain.models.shop.ShoppingCart;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
class ShoppingCartEntityResourceFunctionalTest {

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
        Article article1 = new Article("84002", "art 002", new BigDecimal("0.27"), "prov 2");
        Article article2 = new Article("84003", "art 003", new BigDecimal("12.13"), "prov 3");
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
        String url = this.baseUrl + ShoppingCartResource.SEARCH + "?q=price:5.0";

        ResponseEntity<ShoppingCart[]> response = restTemplate.getForEntity(url, ShoppingCart[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        assertEquals("user2", response.getBody()[0].getUser());
        assertTrue(response.getBody().length > 0);
    }
}