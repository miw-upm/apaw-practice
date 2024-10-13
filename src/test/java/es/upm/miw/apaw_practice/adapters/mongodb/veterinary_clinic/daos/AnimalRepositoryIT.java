package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.AnimalEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class AnimalRepositoryIT {

    @Autowired
    private AnimalRepository animalRepository;

    @Test
    void testFindByName() {
        assertTrue(this.animalRepository.findByName("Lara").isPresent());
        AnimalEntity animal = this.animalRepository.findByName("Lara").get();
        assertEquals(10, animal.getAge());
        assertEquals(LocalDateTime.of(2014, 8, 10, 15, 10), animal.getDateOfService());
        assertEquals("Marcos", animal.getOwnerEntity().getName());
    }
}