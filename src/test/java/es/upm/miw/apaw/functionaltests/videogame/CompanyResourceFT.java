package es.upm.miw.apaw.functionaltests.videogame;

import es.upm.miw.apaw.adapters.resources.videogame.CompanyResource;
import es.upm.miw.apaw.domain.models.videogame.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class CompanyResourceFT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {

        Company company = Company.builder()
                .denomination("company6")
                .sector("sector0")
                .foundationDate(LocalDate.now())
                .build();


        webTestClient.post()
                .uri(CompanyResource.COMPANY)
                .bodyValue(company)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Company.class)
                .value(createdCompany -> assertThat(createdCompany).isNotNull());

    }

    @Test
    void testCreateDenominationConflict() {
        Company company = Company.builder()
                .denomination("company0")
                .sector("sector0")
                .foundationDate(LocalDate.now())
                .build();

        webTestClient.post()
                .uri(CompanyResource.COMPANY)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(company)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

}
