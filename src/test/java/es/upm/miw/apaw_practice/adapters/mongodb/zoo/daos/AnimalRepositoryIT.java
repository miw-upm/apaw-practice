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
        AnimalEntity expected = new AnimalEntity(new Animal("Tiburón Martillo", "Escualo", "Carnívoro"));
        Assertions.assertEquals(expected, this.animalRepository.findAll().get(size - 1));
    }
}
