package es.upm.miw.apaw_practice.domain.models.vet_clinic;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class VetTest {

    @Test
    void builderTest() {
        Vet vet = Vet.builder()
                .vetNumber(6060)
                .name("vet60")
                .surname("surname60")
                .build();
        assertEquals(6060, vet.getVetNumber());
        assertEquals("vet60", vet.getName());
        assertEquals("surname60", vet.getSurname());
    }
}
