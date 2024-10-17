package es.upm.miw.apaw_practice.adapters.rest.company;

import es.upm.miw.apaw_practice.domain.models.company.Department;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.company.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RestTestConfig
public class DepartmentResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateEmployeeCount() {
        this.webTestClient
                .put()
                .uri("/company/departments/Marketing/employee-count") // 修改部门名以避免空格
                .body(BodyInserters.fromValue(50))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Department.class)
                .value(department -> {
                    assertNotNull(department);
                    assertEquals("Marketing", department.getDepartmentName()); // 保持与数据库中的部门名一致
                    assertEquals(50, department.getEmployeeCount());
                });
    }

    @Test
    void testUpdateEmployeeCountNotFound() {
        this.webTestClient
                .put()
                .uri("/company/departments/NonExistentDepartment/employee-count")
                .body(BodyInserters.fromValue(50))
                .exchange()
                .expectStatus().isNotFound();
    }
}

