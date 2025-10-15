package es.upm.miw.apaw.functionaltests.clothingstore;

import es.upm.miw.apaw.adapters.mongodb.DatabaseSeeder;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.StoreRepository;
import es.upm.miw.apaw.adapters.resources.clothingstore.StoreResource;
import es.upm.miw.apaw.domain.models.clothingstore.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class StoreResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private DatabaseSeeder databaseSeeder;

    @Autowired
    private StoreRepository storeRepository;

    @BeforeEach
    void seed() {
        databaseSeeder.reSeedDatabase();
    }

    @Test
    void testDeleteStore_OK() {
        UUID id = storeRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No store seeded"))
                .getId();

        webTestClient
                .delete()
                .uri(StoreResource.STORES + "/" + id)
                .exchange()
                .expectStatus().isNoContent();  // 要求 204

        assertThat(storeRepository.findById(id)).isEmpty();
    }
    @Test
    void testPatchStore_OK() {
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7005");
        Store patchBody = Store.builder().address("Calle Nueva 123").build();

        Store updated = this.webTestClient.patch()
                .uri(StoreResource.STORES + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(patchBody)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Store.class)
                .returnResult()
                .getResponseBody();

        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isEqualTo(id);
        assertThat(updated.getAddress()).isEqualTo("Calle Nueva 123");

    }
}

