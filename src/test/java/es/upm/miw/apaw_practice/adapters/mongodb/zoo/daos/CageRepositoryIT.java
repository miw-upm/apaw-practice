package es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.AnimalEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.CaretakerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.ZooEntity;
import es.upm.miw.apaw_practice.domain.models.zoo.Animal;
import es.upm.miw.apaw_practice.domain.models.zoo.Caretaker;
import es.upm.miw.apaw_practice.domain.models.zoo.Zoo;
import es.upm.miw.apaw_practice.domain.models.zoo.ZooAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

@TestConfig
public class CageRepositoryIT {

    @Autowired
    private CageRepository cageRepository;
    private AnimalEntity[] animals;

    @BeforeEach
    void initializeTestData() {
        animals = new AnimalEntity[]{
                new AnimalEntity(new Animal("Felino", "Omnívoro", 3)),
                new AnimalEntity(new Animal("Mono", "Omnívoro", 14)),
                new AnimalEntity(new Animal("Felino", "Carnívoro", 7)),
                new AnimalEntity(new Animal("Bovino", "Herbívoro", 5))
        };
    }

    @Test
    void testFindByLocationCode() {
        Assertions.assertTrue(this.cageRepository.findByLocationCode("A1").isPresent());
        ZooAddress address = new ZooAddress("Calle Carranza", 22, "28004");
        ZooEntity expectedZoo = new ZooEntity(
                new Zoo(address, 914334789));
        Assertions.assertEquals(expectedZoo, this.cageRepository.findByLocationCode("A1").get().getZoo());
        CaretakerEntity expectedCaretaker = new CaretakerEntity(
                new Caretaker("71679884Q", "Samuel L", "Jackson"));
        Assertions.assertEquals(expectedCaretaker, this.cageRepository.findByLocationCode("A1").get().getCaretaker());
        Assertions.assertEquals(Arrays.asList(animals), this.cageRepository.findByLocationCode("A1").get().getAnimals());
    }

}
