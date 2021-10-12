package es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.AnimalEntity;
import es.upm.miw.apaw_practice.domain.models.zoo.Animal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class AnimalRepositoryIT {

    @Autowired
    private AnimalRepository animalRepository;

    @Test
    void testFindAll() {
        int size = this.animalRepository.findAll().size();
        Assertions.assertNotEquals(0, size);
        AnimalEntity expected = new AnimalEntity(new Animal("Tiburon Martillo", "Escualo", "Carnívoro"));
        Assertions.assertEquals(expected, this.animalRepository.findAll().get(size - 1));
    }

    @Test
    void testFindByName() {
       Assertions.assertTrue(this.animalRepository.findByName("Gato").isPresent());
       Assertions.assertFalse(this.animalRepository.findByName("Gato con botas").isPresent());
    }
}
