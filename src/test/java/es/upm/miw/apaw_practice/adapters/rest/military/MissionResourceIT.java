package es.upm.miw.apaw_practice.adapters.rest.military;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.military.Mission;
import es.upm.miw.apaw_practice.domain.models.military.Unit;
import es.upm.miw.apaw_practice.domain.models.military.Weapon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

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

    @Test
    void testUpdateCodeNameNotFound() {
        Unit unit =
                new Unit("15th Wing", "Air Force", "Zaragoza", Collections.emptyList());
        Mission mission =
                new Mission("Spartan Shield", true, LocalDate.now(), unit, Collections.emptyList());

        this.webTestClient
                .put()
                .uri(MissionResource.MISSIONS + MissionResource.CODENAME_ID, "Spartan Shield")
                .body(BodyInserters.fromValue(mission))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.message")
                .isEqualTo("Not Found Exception (404). Mission codeName: Spartan Shield");
    }

    @Test
    void testUpdateUnitNotFound() {
        Unit unit =
                new Unit("Matacán Flight School", "Air Force", "Salamanca", Collections.emptyList());
        Mission mission =
                new Mission("Inherent Resolve", true, LocalDate.now(), unit, Collections.emptyList());

        this.webTestClient
                .put()
                .uri(MissionResource.MISSIONS + MissionResource.CODENAME_ID, "Atalanta")
                .body(BodyInserters.fromValue(mission))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.message")
                .isEqualTo("Not Found Exception (404). Unit name: Matacán Flight School");
    }

    @Test
    void testUpdateWeaponNotFound() {
        Unit unit =
                new Unit("15th Wing", "Air Force", "Zaragoza", Collections.emptyList());
        Weapon weapon1 =
                new Weapon("SN12345678", "Heckler & Koch", new BigDecimal("1500.50"));
        Weapon weapon2 =
                new Weapon("SN33445566", "Beretta", new BigDecimal("2080.20"));
        Mission mission =
                new Mission("Desert Storm", true, LocalDate.now(), unit, Arrays.asList(weapon1, weapon2));

        this.webTestClient
                .put()
                .uri(MissionResource.MISSIONS + MissionResource.CODENAME_ID, "Desert Storm")
                .body(BodyInserters.fromValue(mission))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.message")
                .isEqualTo("Not Found Exception (404). Weapons with serial codes not found: SN33445566");
    }
}
