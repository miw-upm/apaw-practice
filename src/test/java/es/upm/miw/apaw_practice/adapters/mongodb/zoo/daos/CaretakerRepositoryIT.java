package es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.CaretakerEntity;
import es.upm.miw.apaw_practice.domain.models.zoo.Caretaker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class CaretakerRepositoryIT {

    @Autowired
    private CaretakerRepository caretakerRepository;

    @Test
    void testFindByDni() {
       Assertions.assertTrue(this.caretakerRepository.findByDni("71679884Q").isPresent());
        CaretakerEntity expected = new CaretakerEntity(
                new Caretaker("71679884Q", "Samuel L", "Jackson"));
       Assertions.assertEquals(expected, this.caretakerRepository.findByDni("71679884Q").get());
        Assertions.assertTrue(this.caretakerRepository.findByDni("abecedario").isEmpty());
    }
}
