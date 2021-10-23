package es.upm.miw.apaw_practice.adapters.mongodb.hospital.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.hospital.entities.DiseaseEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class DiseaseRepositoryIT {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Test
    void testFindByAlias() {
        assertTrue(this.diseaseRepository.findByAlias("Conjunctivitis").isPresent());
        DiseaseEntity diseaseEntity = this.diseaseRepository.findByAlias("Conjunctivitis").get();
        assertEquals("Conjunctivitis", diseaseEntity.getAlias());
        assertEquals(Boolean.FALSE, diseaseEntity.getSevere());
        assertEquals("Eye membrane inflamation", diseaseEntity.getDescription());
    }
}