package es.upm.miw.apaw.functionaltests.sports.academy;

import es.upm.miw.apaw.adapters.resources.sports.academy.SportModalityResource;
import es.upm.miw.apaw.domain.models.sports.academy.dtos.UpdateSportMobilityActivation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class SportMobilityResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateActivation(){
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0007");
        var dto = UpdateSportMobilityActivation.builder().active(false).build();
        webTestClient.patch()
                .uri(SportModalityResource.SPORT_MODALITIES + SportModalityResource.ID_ID, id.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .exchange()
                .expectStatus().isOk();
    }
}
