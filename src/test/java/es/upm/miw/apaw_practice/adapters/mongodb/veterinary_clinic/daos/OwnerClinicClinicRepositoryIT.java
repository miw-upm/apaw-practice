package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.OwnerClinicEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class OwnerClinicClinicRepositoryIT {

    @Autowired
    private OwnerClinicRepository ownerClinicRepository;

    @Test
    void testFindByName() {
        assertTrue(this.ownerClinicRepository.findByName("Juan").isPresent());
        OwnerClinicEntity owner = this.ownerClinicRepository.findByName("Juan").get();
        assertEquals("Street Segundo", owner.getAddress());
        assertEquals("841256798", owner.getPhone());
    }
}