package es.upm.miw.apaw.adapters.mongodb.recruiting.daos;

import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.PositionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class PositionRepositoryIT {

    @Autowired
    private PositionRepository positionRepository;

    @Test
    void testFindAll() {
        List<PositionEntity> positions = positionRepository.findAll();

        assertFalse(positions.isEmpty(), "Database filled in by seeder");
        assertEquals(5, positions.size(), "Position size");

        assertTrue(positions.stream().anyMatch(p -> p.getName().equals("ABAP developer")),"Position 'ABAP developer' must exists");
    }

    @Test
    void testCreatePosition() {
        PositionEntity newEntity = PositionEntity.builder()
                .id(UUID.randomUUID())
                .reference(2000)
                .name("New Cloud Architect")
                .description("Expert in AWS and GCP")
                .annualSalary(new BigDecimal("90000.00"))
                .bonusSalary(new BigDecimal("10000.00"))
                .numVacancies(2)
                .build();

        PositionEntity saved = positionRepository.save(newEntity);

        assertNotNull(saved.getId());
        assertEquals("New Cloud Architect", saved.getName());
        assertEquals(2000, saved.getReference());

        Optional<PositionEntity> retrieved = positionRepository.findById(saved.getId());
        assertTrue(retrieved.isPresent());
        assertEquals("New Cloud Architect", retrieved.get().getName());
    }

    @Test
    void testUpdatePosition() {
        Optional<PositionEntity> optionalPosition = positionRepository.findTopByOrderByReferenceDesc();
        assertTrue(optionalPosition.isPresent());

        PositionEntity position = optionalPosition.get();
        position.setNumVacancies(99);
        positionRepository.save(position);

        PositionEntity updated = positionRepository.findById(position.getId()).orElseThrow();
        assertEquals(99, updated.getNumVacancies());
    }

    @Test
    void testFindTopByOrderByReferenceDesc() {
        Optional<PositionEntity> result = positionRepository.findTopByOrderByReferenceDesc();

        assertTrue(result.isPresent(), "At least one position must exist");
        assertEquals(2000, result.get().getReference());
    }
}