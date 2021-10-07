package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class TyreRepositoryIT {

    @Autowired
    private TyreRepository tyreRepository;

    @Test
    void testDeleteByManufacturer(){
        assertEquals(2, this.tyreRepository.deleteByManufacturer("Hankook"));
    }

    @Test
    void testDeleteByManufacturerNone(){
        assertEquals(0, this.tyreRepository.deleteByManufacturer("Evergreen"));
    }
}
