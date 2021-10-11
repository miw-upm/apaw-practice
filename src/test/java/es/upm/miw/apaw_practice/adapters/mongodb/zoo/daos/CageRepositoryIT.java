package es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.AnimalEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.CaretakerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.ZooEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.zoo.Animal;
import es.upm.miw.apaw_practice.domain.models.zoo.Caretaker;
import es.upm.miw.apaw_practice.domain.models.zoo.Zoo;
import es.upm.miw.apaw_practice.domain.models.zoo.ZooAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;

@TestConfig
class CageRepositoryIT {

    @Autowired
    private CageRepository cageRepository;
    @Autowired
    private ZooRepository zooRepository;
    private AnimalEntity[] animals;

    @BeforeEach
    void initializeTestData() {
        animals = new AnimalEntity[]{
                new AnimalEntity(new Animal("Gato", "Felino", "Omnívoro")),
                new AnimalEntity(new Animal("Chimpancé", "Mono", "Omnívoro")),
                new AnimalEntity(new Animal("Tigre dientes de sable", "Felino", "Carnívoro")),
                new AnimalEntity(new Animal("Anjhk", "Bovino", "Herbívoro"))
        };
    }

    @Test
    void testFindByLocationCode() {
        Assertions.assertTrue(this.cageRepository.findByLocationCode("A1").isPresent());
        ZooAddress address = new ZooAddress("Calle Carranza", 22, "28004");
        ZooEntity expectedZoo = new ZooEntity(
                new Zoo(address, 914334789));
        expectedZoo.setId("id1");
        Assertions.assertEquals(expectedZoo, this.cageRepository.findByLocationCode("A1").get().getZoo());
        CaretakerEntity expectedCaretaker = new CaretakerEntity(
                new Caretaker("71679884Q", "Samuel L", "Jackson"));
        Assertions.assertEquals(expectedCaretaker, this.cageRepository.findByLocationCode("A1").get().getCaretaker());
        Assertions.assertEquals(Arrays.asList(animals), this.cageRepository.findByLocationCode("A1").get().getAnimals());
    }

    @Test
    void testFindByZoo() {
        ZooEntity zoo = this.zooRepository.findById("id1")
                .orElseThrow(() -> new NotFoundException("test error"));

        Long countOfAll = this.cageRepository.findByZoo(zoo).count();
        Assertions.assertNotEquals(0, countOfAll);
        Assertions.assertEquals(countOfAll,
                this.cageRepository.findByZoo(zoo)
                        .filter(cage -> cage.getNextFumigation().equals(LocalDate.now()))
                        .count());
    }

}
