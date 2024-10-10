package es.upm.miw.apaw_practice.adapters.mongodb.car.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car.OwnerCar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class OwnerCarPersistenceMongodbIT {

    @Autowired
    private OwnerCarPersistenceMongodb ownerCarPersistence;



    @Test
    public void testReadNotFound(){
        assertThrows(NotFoundException.class, () -> ownerCarPersistence.readByDriverLicense("345SDF"));
    }

    @Test
    void testUpdateNameNotFound() {
        assertThrows(NotFoundException.class, () -> this.ownerCarPersistence.updateName("1", "NewName"));
    }

    @Test
    void testUpdateName() {
        OwnerCar ownerCar = this.ownerCarPersistence.updateName("34VDSG", "Pablo");
        assertNotNull(ownerCar);
        assertEquals("Pablo", ownerCar.getName());
        assertEquals("34VDSG", ownerCar.getDriverLicense());
        assertEquals(LocalDate.of(1978,5,14), ownerCar.getBirthDate());
    }

    @Test
    void testGetTotalCostByDriverLicense() {
        String driverLicense = "UCD253";
        BigDecimal sumCost = this.ownerCarPersistence.getTotalCostByDriverLicense(driverLicense);
        assertEquals(new BigDecimal("400"), sumCost);
    }
}
