package es.upm.miw.apaw_practice.adapters.rest.department;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.department.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class CompanyResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdate() {
        Company company = new Company("direccion nueva","B12312345");

        this.webTestClient
                .put()
                .uri(CompanyResource.COMPANIES + CompanyResource.CIF_ID, "B12312345")
                .body(BodyInserters.fromValue(company))
                .exchange()
                .expectStatus().isOk();
    }
}
