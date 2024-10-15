package es.upm.miw.apaw_practice.adapters.rest.shopping_center;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Employee;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class ShopResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindAllShops() {
        this.webTestClient
                .get()
                .uri(ShopResource.SHOPS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Shop.class)
                .value(shops -> assertFalse(shops.isEmpty()))
                .value(shops -> assertEquals("dir1", shops.get(0).getAddress()));
    }

    @Test
    void testUpdateEmployees() {
        List<Shop> shops = this.webTestClient
                .get()
                .uri(ShopResource.SHOPS)
                .exchange()
                .expectBodyList(Shop.class)
                .returnResult()
                .getResponseBody();
        assert shops != null;
        List<Employee> newEmployees = new ArrayList<>();
        newEmployees.add(new Employee("11122233X", "Pedro", "600000008"));
        newEmployees.add(new Employee("11122233Y", "Janna", "600000009"));
        this.webTestClient
                .put()
                .uri(ShopResource.SHOPS + ShopResource.ID_ID + ShopResource.EMPLOYEES, shops.get(0).getId())
                .body(BodyInserters.fromValue(newEmployees))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateEmployeesNotFound() {
        List<Employee> newEmployees = new ArrayList<>();
        newEmployees.add(new Employee("11122233X", "Pedro", "600000008"));
        newEmployees.add(new Employee("11122233Y", "Janna", "600000009"));
        this.webTestClient
                .put()
                .uri(ShopResource.SHOPS + ShopResource.ID_ID + ShopResource.EMPLOYEES, "not")
                .body(BodyInserters.fromValue(newEmployees))
                .exchange()
                .expectStatus().isNotFound();
    }
}
