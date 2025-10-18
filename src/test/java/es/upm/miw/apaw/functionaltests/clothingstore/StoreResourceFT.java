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
    void testDeleteStore_OK() {
        // 先确认种子存在
        assertThat(storeRepository.findById(SEEDED_STORE_ID)).isPresent();

        webTestClient
                .delete()
                .uri(StoreResource.STORES + "/" + SEEDED_STORE_ID)
                .exchange()
                .expectStatus().isNoContent();   // 204

        assertThat(storeRepository.findById(SEEDED_STORE_ID)).isEmpty();
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
}

