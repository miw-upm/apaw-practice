package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.PharmacySeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.ShopSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.persistence.ArticlePersistenceMongodb;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Pharmacy;
import es.upm.miw.apaw_practice.domain.models.shop.ShoppingCart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@TestConfig
public class DrugPersistenceMongodbIT {

    @Autowired
    private DrugPersistenceMongodb drugPersistenceMongodb;

    @Autowired
    private PharmacySeederService pharmacyService;

    @Test
    void testReadAll() {
        Optional<Drug> drugs = this.drugPersistenceMongodb.readAll()
                .filter(drug -> "A9001".equals(drug.getBarcode()))
                .findFirst();
    }


}
