package es.upm.miw.apaw_practice.adapters.rest.car_hire;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.ModelRepository;
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

import static org.junit.jupiter.api.Assertions.*;


@RestTestConfig
public class VehicleResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    ModelRepository modelRepository;

    @Test
    void testUpdate() {
        assertTrue(this.vehicleRepository.findByVinNumber("VSSZZZ6KZ1R149943").isPresent());
        VehicleEntity oldVehicle = this.vehicleRepository.findByVinNumber("VSSZZZ6KZ1R149943").get();
        assertEquals(new BigDecimal("50"), oldVehicle.getDailyCost());
        assertEquals(25400, oldVehicle.getKilometersAmount());
        assertEquals(true, oldVehicle.getGoodCondition());

        assertTrue(this.modelRepository.findAll().stream()
                .anyMatch(model ->
                        "Opel Insignia".equals(model.getType()) &&
                                "Tipo Berlina, manual".equals(model.getDescription()) &&
                                model.getId() != null &&
                                model.getEnginePower().equals(140) &&
                                1 == model.getVehicleEntities().size() &&
                                "VSSZZZ6KZ1R149943".equals(model.getVehicleEntities().get(0).getVinNumber()) &&
                                model.getVehicleEntities().get(0).getKilometersAmount().equals(25400) &&
                                model.getVehicleEntities().get(0).getDailyCost().equals(new BigDecimal("50")) &&
                                model.getVehicleEntities().get(0).getGoodCondition().equals(true)
                ));

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

        assertTrue(this.modelRepository.findAll().stream()
                .anyMatch(model ->
                        "Opel Insignia".equals(model.getType()) &&
                                "Tipo Berlina, manual".equals(model.getDescription()) &&
                                model.getId() != null &&
                                model.getEnginePower().equals(140) &&
                                1 == model.getVehicleEntities().size() &&
                                "VSSZZZ6KZ1R149943".equals(model.getVehicleEntities().get(0).getVinNumber()) &&
                                model.getVehicleEntities().get(0).getKilometersAmount().equals(40000) &&
                                model.getVehicleEntities().get(0).getDailyCost().equals(new BigDecimal("35")) &&
                                model.getVehicleEntities().get(0).getGoodCondition().equals(false)
                ));
    }
}
