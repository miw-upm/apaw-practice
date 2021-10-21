package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.VehicleRepository;
import es.upm.miw.apaw_practice.domain.models.car_hire.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class VehicleEntityTest {

    @Autowired
    VehicleRepository vehicleRepository;

    @Test
    void testToVehicle() {
        VehicleEntity vehicleEntity = new VehicleEntity();
        if (this.vehicleRepository.findByVinNumber("VSSZZZ6KZ1R149943").isPresent()) {
            vehicleEntity = this.vehicleRepository.findByVinNumber("VSSZZZ6KZ1R149943").get();
        }
        Vehicle vehicle = vehicleEntity.toVehicle();

        assertEquals(vehicleEntity.getId(), vehicle.getId());
        assertEquals(vehicleEntity.getVinNumber(), vehicle.getVinNumber());
        assertEquals(vehicleEntity.getDailyCost(), vehicle.getDailyCost());
        assertEquals(vehicleEntity.getKilometersAmount(), vehicle.getKilometersAmount());
        assertEquals(vehicleEntity.getGoodCondition(), vehicle.getGoodCondition());
    }
}
