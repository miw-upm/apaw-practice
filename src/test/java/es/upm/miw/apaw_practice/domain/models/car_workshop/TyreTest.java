package es.upm.miw.apaw_practice.domain.models.car_workshop;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig

public class TyreTest {

    @Test
    void testBuilder() {
        Tyre tyre = Tyre.builder()
                .manufacturer("Bridgestone")
                .model("Turanza")
                .price(new BigDecimal("74.99"))
                .build();
        assertEquals("Bridgestone", tyre.getManufacturer());
        assertEquals("Turanza", tyre.getModel());
        assertEquals(new BigDecimal("74.99"), tyre.getPrice());
    }
}
