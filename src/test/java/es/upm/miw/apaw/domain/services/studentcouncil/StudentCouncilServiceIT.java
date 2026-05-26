package es.upm.miw.apaw.domain.services.studentcouncil;

import es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos.StudentCouncilSeeder;
import es.upm.miw.apaw.domain.models.studentcouncil.StudentCouncil;
import es.upm.miw.apaw.domain.persistenceports.studentcouncil.StudentCouncilPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class StudentCouncilServiceIT {

    @Autowired
    private StudentCouncilService service;

    @Autowired
    private StudentCouncilPersistence persistence;

    @Autowired
    private StudentCouncilSeeder studentCouncilSeeder;

    @BeforeEach
    void setUp() {
        studentCouncilSeeder.deleteAll();
        studentCouncilSeeder.seedDatabase();
    }

    @Test
    void testUpdateResourcesIT() {
        StudentCouncil council = persistence.readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000")).orElseThrow();
        BigDecimal newResources = BigDecimal.valueOf(15000);

        StudentCouncil updated = service.updateResources(council.getId(), newResources);

        assertEquals(newResources, updated.getResources());
    }

    @Test
    void testSumResourcesByStatement() {
        BigDecimal sum = service.sumResourcesByStatement("Problem1");
        assertThat(sum).isEqualByComparingTo(new BigDecimal("80000.00"));
    }
}