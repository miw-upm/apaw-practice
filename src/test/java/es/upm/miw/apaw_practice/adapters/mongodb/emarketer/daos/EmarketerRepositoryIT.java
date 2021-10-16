package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class EmarketerRepositoryIT {

    @Autowired
    private EmarketerRepository emarketerRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.emarketerRepository.findAll().stream()
                .anyMatch(emarketer ->
                        "Comercializadora A".equals(emarketer.getName()) &&
                                "Madrid".equals(emarketer.getAddress()) &&
                                emarketer.getCupsEntities().get(0).getCups().equals("AAPPZZZ6KZ1R149943") &&
                                emarketer.getPlanEntities().get(0).getDuration() == 24
                ));
    }

}
