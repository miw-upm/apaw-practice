package es.upm.miw.apaw_practice.adapters.rest.Class;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.adapters.rest.football.PrincipalRefereeResource;
import es.upm.miw.apaw_practice.domain.models.Class.Professor;
import es.upm.miw.apaw_practice.domain.models.football.PrincipalReferee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;

@RestTestConfig
public class ProfessorResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ProfessorResource professorResource;

    @Test
    void testCreate() {
        Professor professor = new Professor("wen","china",35, LocalDate.of(2020,12,1));
        this.webTestClient
                .post()
                .uri(ProfessorResource.theProfessor)
                .body(BodyInserters.fromValue(professor))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Professor.class)
                .value(Assertions::assertNotNull);
    }
}
