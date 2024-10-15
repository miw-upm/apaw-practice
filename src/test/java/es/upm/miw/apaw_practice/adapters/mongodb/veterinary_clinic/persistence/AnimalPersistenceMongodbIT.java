package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.VeterinaryClinicSeederService;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Animal;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.OwnerClinic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class AnimalPersistenceMongodbIT {

    @Autowired
    private AnimalPersistenceMongodb animalPersistenceMongodb;

    @Autowired
    private VeterinaryClinicSeederService veterinarySeederService;

    @Test
    void testReadByName() {
        Optional<Animal> animal = Optional.ofNullable(this.animalPersistenceMongodb.readByName("Lara"));
        assertTrue(animal.isPresent());
        assertNotNull(animal.get().getName());
    }

    @Test
    void testUpdate() {
        Optional<Animal> animal = Optional.ofNullable(this.animalPersistenceMongodb.readByName("Lara"));
        assertTrue(animal.isPresent());
        OwnerClinic ownerClinic = new OwnerClinic("Marcos", "Street Toledo", "980453215");
        animal.get().setOwnerClinic(ownerClinic);
        animal.get().setAge(10);
        animal.get().setDateOfService(LocalDateTime.of(2014, 8, 10, 15, 10));
        this.animalPersistenceMongodb.update(animal.get());

        Optional<Animal> updateAnimal = Optional.ofNullable(this.animalPersistenceMongodb.readByName("Lara"));
        assertTrue(updateAnimal.isPresent());
        assertEquals(animal.get().getAge(), updateAnimal.get().getAge());
        assertEquals(animal.get().getOwnerClinic(), ownerClinic);
        assertEquals(animal.get().getDateOfService(), updateAnimal.get().getDateOfService());

        veterinarySeederService.deleteAll();
        veterinarySeederService.seedDatabase();
    }
}