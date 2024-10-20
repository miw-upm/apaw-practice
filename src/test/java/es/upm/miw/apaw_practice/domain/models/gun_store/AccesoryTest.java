package es.upm.miw.apaw_practice.domain.models.gun_store;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccesoryTest {
    @Test
    public void testBuilder() {
        Accesory expectedAccesory = new Accesory(1, "Bayoneta", new BigDecimal("52.99"));
        Accesory actualAccesory = Accesory.builder()
                .accesoryId(1)
                .category("Bayoneta")
                .price(new BigDecimal("52.99"))
                .build();
        assertEquals(expectedAccesory, actualAccesory);
    }
}
