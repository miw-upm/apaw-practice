package es.upm.miw.apaw_practice.adapters.rest.car_workshop;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
class CarResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate(){
        Car car = new Car("1234ABC", true, new Owner("00000000Z"));
        this.webTestClient
                .post()
                .uri(CarResource.CARS)
                .body(BodyInserters.fromValue(car))
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    void testCreateConflictLicensePlate(){
        Car car = new Car("1111AAA", true, new Owner("00000000Z"));
        this.webTestClient
                .post()
                .uri(CarResource.CARS)
                .body(BodyInserters.fromValue(car))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testCreateConflictOwner(){
        Car car = new Car("1234ABC", true, new Owner("123456789A"));
        this.webTestClient
                .post()
                .uri(CarResource.CARS)
                .body(BodyInserters.fromValue(car))
                .exchange()
                .expectStatus().isNotFound();
    }
}
