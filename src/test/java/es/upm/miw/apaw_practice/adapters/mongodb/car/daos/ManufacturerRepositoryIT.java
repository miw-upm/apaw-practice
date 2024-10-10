package es.upm.miw.apaw_practice.adapters.mongodb.car.daos;

import es.upm.miw.apaw_practice.TestConfig;

import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.ManufacturerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import es.upm.miw.apaw_practice.domain.models.car.Manufacturer;



import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ManufacturerRepositoryIT {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Test
    void testFindByName() {


        Optional<ManufacturerEntity> manufacturerEntity = manufacturerRepository.findByName("Ford");
        assertTrue(manufacturerEntity.isPresent());
        assertFalse(manufacturerEntity.isEmpty());
        assertNotNull(manufacturerEntity.get());
        Manufacturer manufacturer = manufacturerEntity.get().toManufacturer();
        assertEquals("Ford", manufacturer.getName());
        assertEquals("USA", manufacturer.getCountry());
        assertEquals(400, manufacturer.getNumberOfEmployees());
    }

    void testFindNameNotExisting(){
        assertTrue(this.manufacturerRepository.findByName("Maserati").isEmpty());
    }
}
