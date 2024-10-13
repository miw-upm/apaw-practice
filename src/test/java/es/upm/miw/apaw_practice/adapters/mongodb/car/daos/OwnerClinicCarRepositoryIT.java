package es.upm.miw.apaw_practice.adapters.mongodb.car.daos;

import es.upm.miw.apaw_practice.TestConfig;


import es.upm.miw.apaw_practice.domain.models.car.OwnerCar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.time.LocalDate;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.OwnerCarEntity;



import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class OwnerClinicCarRepositoryIT {

    @Autowired
    private OwnerCarRepository ownerCarRepository;

    @Test
    void testFindByDriverLicense() {



        Optional<OwnerCarEntity> ownerCarEntity = ownerCarRepository.findByDriverLicense("YYSG34");
        assertTrue(ownerCarEntity.isPresent());
        assertFalse(ownerCarEntity.isEmpty());
        assertNotNull(ownerCarEntity.get());
        OwnerCar ownerCar = ownerCarEntity.get().toOwnerCar();
        assertEquals("Marcos", ownerCar.getName());
        assertEquals("YYSG34", ownerCar.getDriverLicense());
        assertEquals(LocalDate.of(1984,8,30), ownerCar.getBirthDate());
    }

    void testFindDriverLicenseNotExisting(){
        assertTrue(this.ownerCarRepository.findByDriverLicense("JSJYHT").isEmpty());
    }
}