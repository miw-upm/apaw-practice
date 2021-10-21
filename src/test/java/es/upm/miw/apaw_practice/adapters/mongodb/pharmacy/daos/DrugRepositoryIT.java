package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.DrugEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class DrugRepositoryIT {

    @Autowired
    DrugRepository drugRepository;

    @Test
    void testFindByBarcode() {
        assertTrue(this.drugRepository.findByBarcode("A9001").isPresent());
        DrugEntity drug = this.drugRepository.findByBarcode("A9001").get();
        assertEquals("Frenadol Complex", drug.getName());
        assertEquals(true, drug.getCommercialized());
    }
}
