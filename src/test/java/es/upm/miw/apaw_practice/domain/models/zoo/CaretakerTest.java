package es.upm.miw.apaw_practice.domain.models.zoo;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.CaretakerEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@TestConfig
class CaretakerTest {

    @Test
    void testBuilder() {
        Caretaker caretaker = Caretaker.builder()
                .dni("25452510T")
                .name("Niño")
                .surname("Maldito")
                .build();
        Assertions.assertEquals("25452510T", caretaker.getDni());
        Assertions.assertEquals("Niño", caretaker.getName());
        Assertions.assertEquals("Maldito", caretaker.getSurname());
    }
}
