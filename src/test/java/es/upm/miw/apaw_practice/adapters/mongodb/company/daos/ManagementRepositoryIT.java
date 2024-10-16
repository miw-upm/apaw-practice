package es.upm.miw.apaw_practice.adapters.mongodb.company.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.ManagementEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ManagementRepositoryIT {

    @Autowired
    private ManagementRepository managementRepository;

    @BeforeEach
    void seedDatabase() {
        ManagementEntity managementEntity = new ManagementEntity("John Doe", true);
        this.managementRepository.save(managementEntity);
    }

    @Test
    void testFindByName() {
        assertTrue(this.managementRepository.findByName("John Doe").isPresent());
        ManagementEntity management = this.managementRepository.findByName("John Doe").get();
        assertEquals("John Doe", management.getName());
        assertEquals(true, management.isActivated());
    }
}
