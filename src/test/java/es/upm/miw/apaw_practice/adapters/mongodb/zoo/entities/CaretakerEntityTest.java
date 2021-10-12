package es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.zoo.Caretaker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@TestConfig
class CaretakerEntityTest {

    @Test
    void testCaretakerToEntityAndViceversa() {
        Caretaker caretaker = Caretaker.builder()
                .dni("25452510T")
                .name("Niño")
                .surname("Maldito")
                .build();
        CaretakerEntity caretakerEntity = new CaretakerEntity(caretaker);
        Assertions.assertNotNull(caretakerEntity.getId());
        Assertions.assertEquals("25452510T", caretakerEntity.getDni());
        Assertions.assertEquals("Niño", caretakerEntity.getName());
        Assertions.assertEquals("Maldito", caretakerEntity.getSurname());
        Assertions.assertEquals(caretaker, caretakerEntity.toCaretaker());
    }
}
