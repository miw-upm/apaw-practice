package es.upm.miw.apaw.functionaltests.shop;

import es.upm.miw.apaw.domain.models.shop.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static es.upm.miw.apaw.adapters.resources.shop.TagResource.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class TagEntityResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRead() {
        webTestClient.get()
                .uri(TAGS + NAME_ID, "tag3")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Tag.class)
                .value(tag -> {
                    assertThat(tag).isNotNull();
                    assertThat(tag.getDescription()).isEqualTo("tag 3");
                    assertThat(tag.getArticles()).hasSize(1);
                    assertThat(tag.getArticles().getFirst().getBarcode()).isEqualTo("84002");
                    assertThat(tag.getFavourite()).isFalse();
                });
    }

    @Test
    void testReadNotFound() {
        webTestClient.get()
                .uri(TAGS + NAME_ID, "kk")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDelete() {
        webTestClient.delete()
                .uri(TAGS + NAME_ID, "kk")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testFindByArticlesInShoppingCarts() {
        webTestClient.get()
                .uri(TAGS + IN_SHOPPING_CARTS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Tag.class)
                .value(tags -> {
                    assertThat(tags).isNotNull();
                    List<String> tagList = tags.stream()
                            .map(Tag::getName)
                            .toList();
                    assertThat(tagList)
                            .containsExactlyInAnyOrder("tag1", "tag2", "tag3")
                            .doesNotContain("tag4");
                });
    }
}
