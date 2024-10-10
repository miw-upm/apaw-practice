package es.upm.miw.apaw_practice.adapters.rest.car;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.car.Car;
import es.upm.miw.apaw_practice.domain.models.car.OwnerCar;
import es.upm.miw.apaw_practice.domain.models.car.Piece;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.CarPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.OwnerCarPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.PiecePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RestTestConfig
public class CarResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CarPersistence carPersistence;

    @Autowired
    private PiecePersistence piecePersistence;

    @Autowired
    private OwnerCarPersistence ownerCarPersistence;


    @Test
    void testDelete() {
        deleteCar("Scenic").expectStatus().isOk();
    }


    private WebTestClient.ResponseSpec deleteCar(String model) {
        return webTestClient
                .delete()
                .uri(CarResource.CARS + CarResource.MODEL, model)
                .exchange();
    }

    @Test
    void testCreate() {
        OwnerCar ownerCar = this.ownerCarPersistence.readByDriverLicense("GHJFDG");
        Piece piece1 = this.piecePersistence.readByPartNumber("WSDF");
        Piece piece2 = this.piecePersistence.readByPartNumber("SFGE");
        List<Piece> pieceList = new ArrayList<>();
        pieceList.add(piece1);
        pieceList.add(piece2);
        Car car = new Car("Corolla", true, new BigDecimal(45000), ownerCar, pieceList);

        this.webTestClient
                .post()
                .uri(CarResource.CARS)
                .body(BodyInserters.fromValue(car))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Car.class);
    }

    @Test
    void testGetTotalCostByDriverLicense() {
        String driverLicense = "UCD253";
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(CarResource.CARS + CarResource.DRIVERLICENSE)
                        .build(driverLicense))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .value(sum -> {
                    assertEquals(sum, new BigDecimal("400"));
                });
    }



}
