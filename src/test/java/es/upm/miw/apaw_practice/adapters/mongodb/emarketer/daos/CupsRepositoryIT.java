package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class CupsRepositoryIT {

    @Autowired
    private CupsRepository cupsRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.cupsRepository.findAll().stream()
                .anyMatch(cups ->
                        "AAPPZZZ6KZ1R149945".equals(cups.getCups()) &&
                                new BigDecimal("814.12").equals(cups.getEnergy()) &&
                                cups.getId() != null &&
                                LocalDateTime.of(2021, 10, 12, 19, 00 , 00).equals(cups.getRegistrationDate()) &&
                                cups.getCustomerEntity().getName().equals("Gestores S.A")
                ));
    }
}

