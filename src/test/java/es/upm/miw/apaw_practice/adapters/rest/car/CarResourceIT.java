package es.upm.miw.apaw_practice.adapters.rest.car;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.adapters.rest.university.TeacherResource;
import es.upm.miw.apaw_practice.domain.models.car.Car;
import es.upm.miw.apaw_practice.domain.models.university.Teacher;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.CarPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.OwnerCarPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.PiecePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
}
