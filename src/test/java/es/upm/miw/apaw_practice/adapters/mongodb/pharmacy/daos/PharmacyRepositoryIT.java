package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class PharmacyRepositoryIT {

    @Autowired
    PharmacyRepository pharmacyRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.pharmacyRepository.findAll().stream()
                .anyMatch(pharmacy ->
                        "Travesía Cueva de la Mora nº7".equals(pharmacy.getAddress()) &&
                                27680 == pharmacy.getPostalCode() &&
                                pharmacy.getRegistrationNumber() != null &&
                                5 == pharmacy.getDrugEntities().size() &&
                                "A9001".equals(pharmacy.getDrugEntities().get(0).getBarcode()) &&
                                pharmacy.getDrugEntities().get(0).getCommercialized() == true
                ));
    }
}
