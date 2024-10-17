package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.TechniqueEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@TestConfig
public class TechniqueRepositoryIT {
    @Autowired
    private TechniqueRepository techniqueRepository;

    @Test
    void testFindByName() {
        assertTrue(this.techniqueRepository.findByName("Dollyo").isPresent());
        TechniqueEntity techniqueEntity = this.techniqueRepository.findByName("Dollyo").get();
        assertEquals("Dollyo", techniqueEntity.getName());
        assertEquals(1.0, techniqueEntity.getDifficulty());
    }

}
