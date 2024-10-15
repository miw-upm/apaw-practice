package es.upm.miw.apaw_practice.adapters.rest.movies;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.persistence_ports.movies.AwardPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
class AwardResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private AwardPersistence awardPersistence;

    @Test
    void testDeleteNonExisting() {
        deleteAward("NonExistingAward").expectStatus().isOk();
    }

    @Test
    void testDeleteExisting() {
        String existingAward = "Oscar_1981_BestActor";
        assertTrue(awardPersistence.existsByName(existingAward));
        deleteAward(existingAward).expectStatus().isOk();
        assertFalse(awardPersistence.existsByName(existingAward));
    }

    @Test
    void testDeleteIsIdempotent() {
        String existingAward = "GoldenGlobe_2023_BestActress";
        deleteAward(existingAward).expectStatus().isOk();
        deleteAward(existingAward).expectStatus().isOk();
    }

    private WebTestClient.ResponseSpec deleteAward(String awardNameCategoryYear) {
        return webTestClient
                .delete()
                .uri(AwardResource.AWARDS + AwardResource.NAME_ID, awardNameCategoryYear)
                .exchange();
    }
}
