package es.upm.miw.apaw.domain.services.studentcouncil;

import es.upm.miw.apaw.domain.models.studentcouncil.StudentCouncil;
import es.upm.miw.apaw.domain.persistenceports.studentcouncil.StudentCouncilPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class StudentCouncilServiceIT {

    @Autowired
    private StudentCouncilService service;

    @Autowired
    private StudentCouncilPersistence persistence;

    @Test
    void testUpdateResourcesIT() {
        StudentCouncil council = persistence.readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000")).orElseThrow();
        BigDecimal newResources = BigDecimal.valueOf(15000);

        StudentCouncil updated = service.updateResources(council.getId(), newResources);

        assertEquals(newResources, updated.getResources());
    }
}