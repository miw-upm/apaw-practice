package es.upm.miw.apaw.functionaltests.shop;

import es.upm.miw.apaw.adapters.resources.shop.ShoppingCartResource;
import es.upm.miw.apaw.domain.models.shop.Article;
import es.upm.miw.apaw.domain.models.shop.ArticleItem;
import es.upm.miw.apaw.domain.models.shop.ShoppingCart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class ShoppingCartResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdate() {
        Article article1 = Article.builder()
                .barcode("84002").summary("art 002").price(new BigDecimal("0.27"))
                .provider("prov 2").build();

        Article article2 = Article.builder()
                .barcode("84003").summary("art 003").price(new BigDecimal("12.13"))
                .provider("prov 3").build();

        List<ArticleItem> articleItemList = Arrays.asList(
                new ArticleItem(article1, 2, BigDecimal.ONE),
                new ArticleItem(article2, 3, BigDecimal.TEN)
        );

        webTestClient.put()
                .uri(ShoppingCartResource.SHOPPING_CARTS + ShoppingCartResource.ID_ID + ShoppingCartResource.ARTICLE_ITEMS,
                        "ffffffff-ffff-ffff-ffff-ffffffff0000")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(articleItemList)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testFindByPriceGreaterThan() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ShoppingCartResource.SHOPPING_CARTS)
                        .queryParam("price", 5.0)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ShoppingCart.class)
                .value(carts -> assertThat(carts)
                        .extracting(ShoppingCart::getId)
                        .contains(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0021")));
    }
}
