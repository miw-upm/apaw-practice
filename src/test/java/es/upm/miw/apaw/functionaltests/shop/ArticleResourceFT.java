package es.upm.miw.apaw.functionaltests.shop;

import es.upm.miw.apaw.adapters.resources.shop.ArticleResource;
import es.upm.miw.apaw.domain.models.shop.Article;
import es.upm.miw.apaw.domain.models.shop.ArticlePriceUpdating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class ArticleResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Article article = Article.builder()
                .barcode("666004")
                .summary("art restclients")
                .price(new BigDecimal("3.00"))
                .build();

        webTestClient.post()
                .uri(ArticleResource.ARTICLES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(article)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Article.class)
                .value(created -> assertThat(created).isNotNull());
    }

    @Test
    void testCreateBarcodeConflict() {
        Article article = Article.builder()
                .barcode("84001")
                .summary("repeated")
                .price(new BigDecimal("3.00"))
                .build();

        webTestClient.post()
                .uri(ArticleResource.ARTICLES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(article)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testCreateBadRequest() {
        Article article = Article.builder()
                .barcode("84001")
                .summary("repeated") // falta price
                .build();

        webTestClient.post()
                .uri(ArticleResource.ARTICLES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(article)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testUpdatePrices() {
        Article article = Article.builder()
                .barcode("666005")
                .summary("art restclients")
                .price(new BigDecimal("3.00"))
                .build();

        webTestClient.post()
                .uri(ArticleResource.ARTICLES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(article)
                .exchange()
                .expectStatus().isOk();

        List<ArticlePriceUpdating> updates = List.of(
                new ArticlePriceUpdating("666005", new BigDecimal("3.33"))
        );

        webTestClient.patch()
                .uri(ArticleResource.ARTICLES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updates)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdatePricesNotFound() {
        List<ArticlePriceUpdating> updates = List.of(
                new ArticlePriceUpdating("0", BigDecimal.ONE)
        );

        webTestClient.patch()
                .uri(ArticleResource.ARTICLES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updates)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testSearchByProviderAndPriceGreaterThan() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ArticleResource.ARTICLES)
                        .queryParam("provider", "prov 1")
                        .queryParam("price", "1.02")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Article.class)
                .value(articles -> {
                    assertThat(articles).isNotEmpty();
                    assertThat(articles)
                            .extracting(Article::getProvider)
                            .allMatch("prov 1"::equals);
                    assertThat(articles)
                            .extracting(Article::getPrice)
                            .allMatch(price -> price.compareTo(new BigDecimal("1.02")) > 0);
                });
    }

    @Test
    void testSearchByProviderAndPriceGreaterThanBadRequest() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ArticleResource.ARTICLES)
                        .queryParam("provider", "prov 1")
                        .build())
                .exchange()
                .expectStatus().isBadRequest();
    }
}

