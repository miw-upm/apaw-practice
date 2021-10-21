package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_hire.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
class VehiclePersistenceMongodbIT {

    @Autowired
    VehiclePersistenceMongodb vehiclePersistenceMongodb;

    @Test
    void testReadByVinNumberNotFound() {
        assertThrows(NotFoundException.class, () -> this.vehiclePersistenceMongodb.readByVinNumber("invented"));
    }

    @Test
    void testReadByVinNumberAndUpdate() {
        Vehicle vehicleInvented = Vehicle.builder().vinNumber("invented").dailyCost(new BigDecimal("1")).kilometersAmount(1).goodCondition(Boolean.TRUE).build();
        assertThrows(NotFoundException.class, () -> this.vehiclePersistenceMongodb.update(vehicleInvented));

        Vehicle vehicle = this.vehiclePersistenceMongodb.readByVinNumber("GYWKAS8AHBD284620");
        assertEquals("GYWKAS8AHBD284620", vehicle.getVinNumber());
        assertEquals(0, vehicle.getDailyCost().compareTo(new BigDecimal("30")));
        assertEquals(52000, vehicle.getKilometersAmount());
        assertEquals(Boolean.TRUE, vehicle.getGoodCondition());

        vehicle.setDailyCost(new BigDecimal("1"));
        vehicle.setKilometersAmount(20);
        vehicle.setGoodCondition(Boolean.FALSE);

        Vehicle vehicleUpdated = this.vehiclePersistenceMongodb.update(vehicle);
        assertEquals(0, vehicleUpdated.getDailyCost().compareTo(new BigDecimal("1")));
        assertEquals(20, vehicleUpdated.getKilometersAmount());
        assertEquals(Boolean.FALSE, vehicleUpdated.getGoodCondition());
    }
}
