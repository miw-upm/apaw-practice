package es.upm.miw.apaw.functionaltests.clothingstore;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.StoreRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.clothingstoreSeeder;
import es.upm.miw.apaw.adapters.resources.clothingstore.StoreResource;
import es.upm.miw.apaw.domain.models.clothingstore.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class StoreResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private clothingstoreSeeder clothingstoreSeeder;

    @Autowired
    private StoreRepository storeRepository;

    private static final UUID SEEDED_STORE_ID =
            UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7005");

    @BeforeEach
    void resetDb() {
        clothingstoreSeeder.deleteAll();
        clothingstoreSeeder.seedDatabase();
    }

    @Test
    void testReadById_OK() {
        Store store = this.webTestClient.get()
                .uri(StoreResource.STORES + "/" + SEEDED_STORE_ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Store.class)
                .returnResult()
                .getResponseBody();

        assertThat(store).isNotNull();
        assertThat(store.getId()).isEqualTo(SEEDED_STORE_ID);
    }

    @Test
    void testReadById_notFound() {
        this.webTestClient.get()
                .uri(StoreResource.STORES + "/" + UUID.randomUUID())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateStore_OK() {
        Store body = Store.builder()
                .name("Madrid Fashion Updated")
                .address("Calle Actualizada 456")
                .build();

        Store updated = this.webTestClient.put()
                .uri(StoreResource.STORES + "/" + SEEDED_STORE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Store.class)
                .returnResult()
                .getResponseBody();

        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isEqualTo(SEEDED_STORE_ID);
        assertThat(updated.getName()).isEqualTo("Madrid Fashion Updated");
        assertThat(updated.getAddress()).isEqualTo("Calle Actualizada 456");
    }

    @Test
    void testUpdateStore_notFound() {
        Store body = Store.builder()
                .name("Madrid Fashion Updated")
                .address("Calle Actualizada 456")
                .build();

        this.webTestClient.put()
                .uri(StoreResource.STORES + "/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteStore_OK() {
        assertThat(storeRepository.findById(SEEDED_STORE_ID)).isPresent();

        webTestClient
                .delete()
                .uri(StoreResource.STORES + "/" + SEEDED_STORE_ID)
                .exchange()
                .expectStatus().isNoContent();

        assertThat(storeRepository.findById(SEEDED_STORE_ID)).isEmpty();
    }

    @Test
    void testDeleteStore_notFound() {
        this.webTestClient.delete()
                .uri(StoreResource.STORES + "/" + UUID.randomUUID())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchStore_OK() {
        Store patchBody = Store.builder().address("Calle Nueva 123").build();

        Store updated = this.webTestClient.patch()
                .uri(StoreResource.STORES + "/" + SEEDED_STORE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(patchBody)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Store.class)
                .returnResult()
                .getResponseBody();

        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isEqualTo(SEEDED_STORE_ID);
        assertThat(updated.getAddress()).isEqualTo("Calle Nueva 123");
    }

    @Test
    void testPatchStore_notFound() {
        Store patchBody = Store.builder().address("Calle Nueva 123").build();

        this.webTestClient.patch()
                .uri(StoreResource.STORES + "/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(patchBody)
                .exchange()
                .expectStatus().isNotFound();
    }
}
