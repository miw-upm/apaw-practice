package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class DispensingRepositoryIT {

    @Autowired
    DispensingRepository dispensingRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.dispensingRepository.findAll().stream()
                .anyMatch(dispensing ->
                        LocalDateTime.of(2021, 7, 2, 21, 34).equals(dispensing.getDispensingTimestamp()) &&
                                dispensing.getActiveIngredientEntities().get(0).getDrugEntity().getBarcode().equals("A9001")
                ));
    }
}
