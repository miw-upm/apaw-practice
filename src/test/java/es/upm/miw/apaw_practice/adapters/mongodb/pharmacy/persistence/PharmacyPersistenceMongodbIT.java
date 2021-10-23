package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.PharmacySeederService;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Pharmacy;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.PharmacyPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class PharmacyPersistenceMongodbIT {

    @Autowired
    PharmacyPersistence pharmacyPersistence;

    @Autowired
    private PharmacySeederService pharmacySeederService;

    @Test
    void testCreateAndUpdate() {
        Pharmacy pharmacy =
                new Pharmacy("666666","Calle Estrella nº35", 27009, List.of(new Drug("A9001", "Frenadol Complex", true, new BigDecimal(5.39))));
        Pharmacy pharmacyBD = this.pharmacyPersistence.create(pharmacy);
        pharmacyBD.setPostalCode(1234);
        this.pharmacyPersistence.update(pharmacyBD.getRegistrationNumber(), pharmacyBD);
        pharmacyBD = this.pharmacyPersistence.read(pharmacyBD.getRegistrationNumber());
        assertEquals(1234, pharmacyBD.getPostalCode());
        pharmacySeederService.deleteAll();
        pharmacySeederService.seedDatabase();
    }

}
