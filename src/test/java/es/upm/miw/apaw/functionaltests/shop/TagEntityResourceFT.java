package es.upm.miw.apaw.functionaltests.shop;

import es.upm.miw.apaw.domain.models.shop.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static es.upm.miw.apaw.adapters.rest.shop.TagResource.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class TagEntityResourceFT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + TAGS;
    }

    @Test
    void testRead() {
        String url = this.baseUrl + NAME_ID.replace("{name}", "tag3");

        ResponseEntity<Tag> response = restTemplate.getForEntity(url, Tag.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getDescription()).isEqualTo("tag 3");
        assertThat(response.getBody().getArticles()).hasSize(1);
        assertThat(response.getBody().getArticles().getFirst().getBarcode()).isEqualTo("84002");
        assertThat(response.getBody().getFavourite()).isFalse();
    }

    @Test
    void testReadNotFound() {
        String url = this.baseUrl + NAME_ID.replace("{name}", "kk");

        ResponseEntity<Void> response = restTemplate.getForEntity(url, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testDelete() {
        String url = this.baseUrl + NAME_ID.replace("{name}", "kk");

        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testFindByArticlesInShoppingCarts() {
        String url = this.baseUrl + IN_SHOPPING_CARTS;

        ResponseEntity<Tag[]> response = restTemplate.getForEntity(url, Tag[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        List<String> tagList = Arrays.stream(response.getBody())
                .map(Tag::getName)
                .toList();
        assertThat(tagList)
                .containsExactlyInAnyOrder("tag1", "tag2", "tag3")
                .doesNotContain("tag4");
    }

}