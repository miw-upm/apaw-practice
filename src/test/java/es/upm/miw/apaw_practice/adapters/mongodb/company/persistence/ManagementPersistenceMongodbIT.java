package es.upm.miw.apaw_practice.adapters.mongodb.company.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.company.Management;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestConfig
public class ManagementPersistenceMongodbIT {

    @Autowired
    private ManagementPersistenceMongodb managementPersistenceMongodb;

    @Test
    void testCreateAndRead() {
        Management management = new Management(UUID.randomUUID().toString(),"John Doe", true);
        Management managementSaved = this.managementPersistenceMongodb.create(management);
        assertNotNull(managementSaved);
        assertEquals("John Doe", managementSaved.getName());
        assertEquals(true, managementSaved.isActivated());
    }

    @Test
    void testCreateAndReadInactiveManagement() {
        Management management = new Management(UUID.randomUUID().toString(),"Jane Doe", false);
        Management managementSaved = this.managementPersistenceMongodb.create(management);
        assertNotNull(managementSaved);
        assertEquals("Jane Doe", managementSaved.getName());
        assertEquals(false, managementSaved.isActivated());
    }
}

