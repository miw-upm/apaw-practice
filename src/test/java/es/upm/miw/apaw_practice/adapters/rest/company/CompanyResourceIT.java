package es.upm.miw.apaw_practice.adapters.rest.company;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.company.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw_practice.adapters.rest.company.CompanyResource.COMPANIES;
import static es.upm.miw.apaw_practice.adapters.rest.company.CompanyResource.SEARCH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RestTestConfig
public class CompanyResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindByCompanyname() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(COMPANIES + SEARCH)
                                .queryParam("companyname", "TechCorp")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Company.class)
                .value(Assertions::assertNotNull)
                .value(company -> {
                    assertEquals("TechCorp", company.getCompanyname());
                    assertEquals("New York", company.getLocation());
                    assertNotNull(company.getDepartments());
                });
    }

    @Test
    void testFindByCompanynameNotFound() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(COMPANIES + SEARCH)
                                .queryParam("companyname", "NonExistentCompany")
                                .build())
                .exchange()
                .expectStatus().isNotFound();
    }
}