package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.persistence.ArticlePersistenceMongodb;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class VetPersistenceMongodbIT {

    @Autowired
    private VetPersistenceMongodb vetPersistenceMongodb;

    @Test
    void notExistVetNumberTest() {
        assertFalse(this.vetPersistenceMongodb.existVetNumber(777));
    }

    @Test
    void existVetNumberTest() {
        assertTrue(this.vetPersistenceMongodb.existVetNumber(111));
    }

}
