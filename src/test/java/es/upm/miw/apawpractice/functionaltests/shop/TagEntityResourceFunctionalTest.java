package es.upm.miw.apawpractice.functionaltests.shop;

import es.upm.miw.apawpractice.domain.models.shop.Tag;
import es.upm.miw.apawpractice.functionaltests.RestTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static es.upm.miw.apawpractice.adapters.rest.shop.TagResource.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
class TagEntityResourceFunctionalTest {

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
        assertNotNull(response.getBody());
        assertEquals("tag 3", response.getBody().getDescription());
        assertEquals(1, response.getBody().getArticles().size());
        assertEquals("84002", response.getBody().getArticles().getFirst().getBarcode());
        assertFalse(response.getBody().getFavourite());
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
        assertNotNull(response.getBody());
        List<String> tagList = Arrays.stream(response.getBody())
                .map(Tag::getName)
                .toList();
        assertTrue(tagList.containsAll(Arrays.asList("tag1", "tag2", "tag3")));
        assertFalse(tagList.contains("tag4"));
    }

}