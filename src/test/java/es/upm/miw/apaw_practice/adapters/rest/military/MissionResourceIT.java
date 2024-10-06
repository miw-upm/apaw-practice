package es.upm.miw.apaw_practice.adapters.rest.military;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.military.Mission;
import es.upm.miw.apaw_practice.domain.models.military.Soldier;
import es.upm.miw.apaw_practice.domain.models.military.Unit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestTestConfig
public class MissionResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdate() {
        Unit unit =
                new Unit("15th Wing", "Air Force", "Zaragoza", Collections.emptyList());
        Mission mission =
                new Mission("Inherent Resolve", true, LocalDate.now(), unit, Collections.emptyList());

        this.webTestClient
                .put()
                .uri(MissionResource.MISSIONS + MissionResource.CODENAME_ID, "Atalanta")
                .body(BodyInserters.fromValue(mission))
                .exchange()
                .expectStatus().isOk();
    }

    // TODO: Test Update codeName not found
    // TODO: Test Update unit not found
}
