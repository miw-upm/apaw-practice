package es.upm.miw.apaw_practice.adapters.rest.department;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.department.DepartmentEmployee;
import es.upm.miw.apaw_practice.domain.models.department.DepartmentEmployeeIsActiveUpdating;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestTestConfig
public class DepartmentEmployeeResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        DepartmentEmployee departmentEmployee =
                new DepartmentEmployee("08553821F", LocalDate.of(1980,4,11), true);
        this.webTestClient
                .post()
                .uri(DepartmentEmployeeResource.DEPARTMENT_EMPLOYEES)
                .body(BodyInserters.fromValue(departmentEmployee))
                .exchange()
                .expectStatus().isOk()
                .expectBody(DepartmentEmployee.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testCreateConflict() {
        DepartmentEmployee departmentEmployee =
                new DepartmentEmployee("00523821F", LocalDate.of(1984,8,27), false);
        this.webTestClient
                .post()
                .uri(DepartmentEmployeeResource.DEPARTMENT_EMPLOYEES)
                .body(BodyInserters.fromValue(departmentEmployee))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdateIsActive() {
        List<DepartmentEmployeeIsActiveUpdating> departmentEmployeeIsActiveUpdatings = Arrays.asList(
                new DepartmentEmployeeIsActiveUpdating("00523821F", true)
        );
        this.webTestClient
                .patch()
                .uri(DepartmentEmployeeResource.DEPARTMENT_EMPLOYEES)
                .body(BodyInserters.fromValue(departmentEmployeeIsActiveUpdatings))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateIsActiveNotFound() {
        List<DepartmentEmployeeIsActiveUpdating> departmentEmployeeIsActiveUpdatings = Arrays.asList(
                new DepartmentEmployeeIsActiveUpdating("0", true)
        );
        this.webTestClient
                .patch()
                .uri(DepartmentEmployeeResource.DEPARTMENT_EMPLOYEES)
                .body(BodyInserters.fromValue(departmentEmployeeIsActiveUpdatings))
                .exchange()
                .expectStatus().isNotFound();
    }
}
