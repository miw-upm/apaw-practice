package es.upm.miw.apaw_practice.adapters.rest.car_hire;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.VehicleRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.VehicleEntity;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.car_hire.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RestTestConfig
public class VehicleResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    VehicleRepository vehicleRepository;

    @Test
    void testUpdate() {
        assertTrue(this.vehicleRepository.findByVinNumber("VSSZZZ6KZ1R149943").isPresent());
        VehicleEntity oldVehicle = this.vehicleRepository.findByVinNumber("VSSZZZ6KZ1R149943").get();
        assertEquals(new BigDecimal("50"), oldVehicle.getDailyCost());
        assertEquals(25400, oldVehicle.getKilometersAmount());
        assertEquals(true, oldVehicle.getGoodCondition());

        Vehicle vehicle = new Vehicle("VSSZZZ6KZ1R149943", new BigDecimal("35"), 40000, false);
        this.webTestClient
                .put()
                .uri(VehicleResource.VEHICLES + VehicleResource.VIN_NUMBER, vehicle.getVinNumber())
                .body(BodyInserters.fromValue(vehicle))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Vehicle.class)
                .value(Assertions::assertNotNull);

        assertTrue(this.vehicleRepository.findByVinNumber("VSSZZZ6KZ1R149943").isPresent());
        VehicleEntity updatedVehicle = this.vehicleRepository.findByVinNumber("VSSZZZ6KZ1R149943").get();
        assertEquals(new BigDecimal("35"), updatedVehicle.getDailyCost());
        assertEquals(40000, updatedVehicle.getKilometersAmount());
        assertEquals(false, updatedVehicle.getGoodCondition());
    }
}
