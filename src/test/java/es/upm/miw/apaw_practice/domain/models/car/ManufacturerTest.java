package es.upm.miw.apaw_practice.domain.models.car;


import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class ManufacturerTest {


    @Test
    void testManufacturerBuilder() {
        Manufacturer instance = Manufacturer.builder()
                .name("BYD")
                .country("China")
                .numberOfEmployees(3400)
                .build();
        assertEquals("BYD", instance.getName());
        assertEquals("China", instance.getCountry());
        assertEquals(3400, instance.getNumberOfEmployees());
    }
}
