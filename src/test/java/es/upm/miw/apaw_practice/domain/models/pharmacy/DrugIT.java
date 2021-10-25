package es.upm.miw.apaw_practice.domain.models.pharmacy;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class DrugIT {

    private Drug drug;

    @BeforeEach
    void initializeDrug() {
        drug = Drug.builder().barcode("A9001").name("Frenadol Complex").commercialized(true).price(new BigDecimal("5.39")).build();
    }

    @Test
    void testToString() {
        assertEquals("Drug{" +
                "barcode='" + "A9001" + '\'' +
                ", name='" + "Frenadol Complex" + '\'' +
                ", commercialized=" + true +
                ", price=" + "5.39" +
                '}', drug.toString());
    }
}
