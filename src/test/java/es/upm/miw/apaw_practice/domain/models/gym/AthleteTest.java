package es.upm.miw.apaw_practice.domain.models.gym;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class AthleteTest {
    @Test
    void testBuilder() {
        Athlete athlete = new Athlete.Builder().nie("12345678a").name("ada").familyName("perez").build();

        assertEquals("12345678a", athlete.getNie());
        assertEquals("ada", athlete.getName());
        assertEquals("perez", athlete.getFamilyName());

    }

}