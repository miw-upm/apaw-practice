package es.upm.miw.apaw_practice.adapters.rest.wushu_sport;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.CompetitionForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.Duration;

@RestTestConfig
public class CompetitionFormResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate(){
        CompetitionForm competitionForm = new CompetitionForm(8.00, Duration.ofMinutes(1).plusSeconds(20), "changquan");
        this.webTestClient
                .post()
                .uri(CompetitionFormResource.COMPETITION_FORM)
                .body(BodyInserters.fromValue(competitionForm))
                .exchange()
                .expectStatus().isOk()
                .expectBody(CompetitionForm.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testGetTotalDurationBySchoolName(){
        this.webTestClient
                .get()
                .uri(CompetitionFormResource.COMPETITION_FORM + CompetitionFormResource.DURATION + CompetitionFormResource.SCHOOL_NAME_ID, "kk")
                .exchange()
                .expectStatus().isNotFound();

    }

}
