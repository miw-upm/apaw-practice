package es.upm.miw.apaw.adapters.mongodb.recruiting.daos;

import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.PositionEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.persistance.PositionPersistenceMongodb;
import es.upm.miw.apaw.domain.models.recruiting.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class PositionRepositoryIT {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private PositionPersistenceMongodb positionPersistenceMongodb;

    @BeforeEach
    void setUp() {
        positionRepository.deleteAll();

        // Adding some data test
        positionRepository.save(PositionEntity.builder()
                .id(UUID.randomUUID())
                .reference(1)
                .name("Intern")
                .annualSalary(new BigDecimal("20000"))
                .numVacancies(2)
                .build());

        positionRepository.save(PositionEntity.builder()
                .id(UUID.randomUUID())
                .reference(5)
                .name("Senior Engineer")
                .annualSalary(new BigDecimal("60000"))
                .numVacancies(1)
                .build());

        positionRepository.save(PositionEntity.builder()
                .id(UUID.randomUUID())
                .reference(3)
                .name("Mid-level Developer")
                .annualSalary(new BigDecimal("40000"))
                .numVacancies(2)
                .build());
    }

    @Test
    void testFindTopByOrderByReferenceDesc_ReturnsHighestReference() {
        Optional<PositionEntity> result = positionRepository.findTopByOrderByReferenceDesc();

        assertTrue(result.isPresent());
        assertEquals(5, result.get().getReference());
        assertEquals("Senior Engineer", result.get().getName());
    }

    @Test
    void testCreatePosition_AutoGeneratesReference() {
        // Reference must be auto generated
        Position newPosition = Position.builder()
                .name("New Backend Developer")
                .description("Responsible for APIs")
                .annualSalary(new BigDecimal("50000"))
                .bonusSalary(new BigDecimal("3000"))
                .numVacancies(2)
                .build();

        Position saved = positionPersistenceMongodb.create(newPosition);

        System.out.println("✅ Reference auto generated: " + saved.getReference());
        assertEquals(6, saved.getReference()); // max was 5 → new one must be 6
        assertEquals("New Backend Developer", saved.getName());
    }

    @Test
    void testCreatePosition_WhenNoExistingReferences() {
        positionRepository.deleteAll();

        Position newPosition = Position.builder()
                .name("First Position")
                .annualSalary(new BigDecimal("40000"))
                .numVacancies(1)
                .build();

        Position saved = positionPersistenceMongodb.create(newPosition);

        System.out.println("✅ Reference auto generated (without records): " + saved.getReference());
        assertEquals(1, saved.getReference()); // starts in 1
    }
}
