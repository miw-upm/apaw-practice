package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.VehicleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class VehicleRepositoryIT {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    void testFindByVinNumber() {
        assertTrue(this.vehicleRepository.findByVinNumber("VSSZZZ6KZ1R149943").isPresent());
        VehicleEntity vehicle = this.vehicleRepository.findByVinNumber("VSSZZZ6KZ1R149943").get();
        assertEquals(0, new BigDecimal("50").compareTo(vehicle.getDailyCost()));
        assertEquals(25400, vehicle.getKilometersAmount());
        assertEquals(true, vehicle.getGoodCondition());
    }
}
