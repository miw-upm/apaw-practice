package es.upm.miw.apaw_practice.adapters.rest.veterinary_clinic;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Animal;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
class EmployeeResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRead() {
        this.webTestClient
                .get()
                .uri(EmployeeResource.EMPLOYEES + EmployeeResource.NAME_EMPLOYEE, "Paco")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Employee.class)
                .value(employee -> {
                    assertTrue(employee.isDoctor());
                    assertEquals(1, employee.getAnimals().size());
                    assertTrue(employee.getAnimals().stream()
                            .map(Animal::getName)
                            .toList()
                            .contains("Lara"));
                });
    }

    @Test
    void testReadNotFound() {
        this.webTestClient
                .get()
                .uri(EmployeeResource.EMPLOYEES + EmployeeResource.NAME_EMPLOYEE, "Lucas")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteByName() {
        this.webTestClient
                .delete()
                .uri(EmployeeResource.EMPLOYEES + EmployeeResource.NAME_EMPLOYEE, "Lucas")
                .exchange()
                .expectStatus().isOk();
    }
}