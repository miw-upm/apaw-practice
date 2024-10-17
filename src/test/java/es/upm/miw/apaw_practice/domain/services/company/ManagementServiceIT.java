package es.upm.miw.apaw_practice.domain.services.company;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.company.Management;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestConfig
public class ManagementServiceIT {

    @Autowired
    private ManagementService managementService;

    @Test
    void testCreateAndRead() {
        Management management = new Management(UUID.randomUUID().toString(),"John Doe", true);
        Management createdManagement = this.managementService.create(management);
        assertNotNull(createdManagement);
        assertEquals("John Doe", createdManagement.getName());
        assertEquals(true, createdManagement.isActivated());
    }
}

