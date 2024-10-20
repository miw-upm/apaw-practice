package es.upm.miw.apaw_practice.adapters.rest.company;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.company.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static es.upm.miw.apaw_practice.adapters.rest.company.CompanyResource.*;
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

    @Test
    void testUpdateIndustry() {
        String companyName = "TechCorp";
        String newIndustry = "Renewable Energy";

        this.webTestClient
                .patch()
                .uri(uriBuilder ->
                        uriBuilder.path(COMPANIES + INDUSTRY)
                                .queryParam("companyname", companyName)
                                .build())
                .bodyValue(newIndustry)
                .exchange()
                .expectStatus().isOk();

        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(COMPANIES + SEARCH)
                                .queryParam("companyname", companyName)
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Company.class)
                .value(company -> assertEquals(newIndustry, company.getIndustry()));
    }

    @Test
    void testGetHighestExpenseAmountByLocation() {
        String location = "New York";
        BigDecimal expectedHighestExpense = new BigDecimal("0");

        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(COMPANIES + LOCATION_HIGHEST_EXPENSE)
                                .build(location))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .value(actualHighestExpense -> assertEquals(expectedHighestExpense, actualHighestExpense));
    }

    @Test
    void testFindManagementNamesByIndustryAndDescription() {
        String industry = "Technology";
        String description = "Office Supplies";

        // 发送请求并获取管理者名称列表
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(COMPANIES + "/management-search")
                                .queryParam("industry", industry)
                                .queryParam("description", description)
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .value(managementNames -> {
                    assertNotNull(managementNames);
                    assertEquals(1, managementNames.size());
                    assertEquals("[\"John Doe\"]", managementNames.get(0));  // 根据种子数据的预期结果
                });
    }
}
