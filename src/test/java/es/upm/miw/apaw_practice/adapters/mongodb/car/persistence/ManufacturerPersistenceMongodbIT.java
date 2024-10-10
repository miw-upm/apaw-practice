package es.upm.miw.apaw_practice.adapters.mongodb.car.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.car.Manufacturer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ManufacturerPersistenceMongodbIT {

    @Autowired
    private ManufacturerPersistenceMongodb manufacturerPersistence;

    @Test
    public void testReadByName(){
        Manufacturer manufacturer = this.manufacturerPersistence.readByName("Tesla");
        assertEquals("Tesla", manufacturer.getName());
        assertEquals("USA", manufacturer.getCountry());
        assertEquals(2000, manufacturer.getNumberOfEmployees());
    }

    @Test
    public void testUpdate(){
        Manufacturer manufacturer = this.manufacturerPersistence.readByName("Tesla");
        assertEquals("Tesla", manufacturer.getName());
        assertEquals("USA", manufacturer.getCountry());
        assertEquals(2000, manufacturer.getNumberOfEmployees());
    }

    @Test
    void testNameNotExists() {
        assertFalse(manufacturerPersistence.existName("BMW"));
    }

    @Test
    void testNameExists() {
        assertTrue(manufacturerPersistence.existName("Tesla"));
    }


}
