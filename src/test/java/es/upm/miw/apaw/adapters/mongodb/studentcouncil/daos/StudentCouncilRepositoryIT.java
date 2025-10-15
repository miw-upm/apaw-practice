package es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos;


import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.StudentCouncilEntity;
import es.upm.miw.apaw.adapters.mongodb.studentcouncil.persistence.StudentCouncilPersistenceMongodb;
import es.upm.miw.apaw.domain.models.studentcouncil.StudentCouncil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class StudentCouncilRepositoryIT {

    @Autowired
    private StudentCouncilRepository repository;
    @Autowired
    private StudentCouncilSeeder seeder;

    @BeforeEach
    void setUp() {
        seeder.deleteAll();
        seeder.seedDatabase();
    }

    @Test
    void testSaveAndFind() {
        StudentCouncilEntity entity = StudentCouncilEntity.builder()
                .id(UUID.randomUUID())
                .council("ETSII")
                .site("Madrid")
                .resources(BigDecimal.valueOf(10000))
                .build();

        repository.save(entity);
        StudentCouncilEntity found = repository.findById(entity.getId()).orElseThrow();

        assertEquals(entity.getResources(), found.getResources());
        assertEquals(entity.getCouncil(), found.getCouncil());
    }
    @Test
    void testSumResourcesByStatement() {
        BigDecimal sum = repository.findAll().stream()
                .map(StudentCouncilEntity::toStudentCouncil)
                .filter(c -> c.getRepresentatives() != null)
                .filter(c -> c.getRepresentatives().stream()
                        .filter(r -> r.getTopics() != null)
                        .anyMatch(r -> r.getTopics().stream()
                                .anyMatch(issue -> "Problem1".equalsIgnoreCase(issue.getStatement()))
                        )
                )
                .map(StudentCouncil::getResources)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        assertThat(sum).isEqualByComparingTo(new BigDecimal("80000.00"));
    }
}