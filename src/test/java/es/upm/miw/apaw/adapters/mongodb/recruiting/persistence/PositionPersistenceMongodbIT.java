package es.upm.miw.apaw.adapters.mongodb.recruiting.persistence;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.PositionRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.RecruitingSeeder;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.PositionEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.persistance.PositionPersistenceMongodb;
import es.upm.miw.apaw.domain.models.recruiting.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class PositionPersistenceMongodbIT {

    @Autowired
    private PositionPersistenceMongodb positionPersistenceMongodb;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private RecruitingSeeder recruitingSeeder;

    @BeforeEach
    void setUp() {
        recruitingSeeder.deleteAll();
        recruitingSeeder.seedDatabase();
    }

    @Test
    void testCreate_NewPosition_ShouldAutoIncrementReference() {
        Position newPosition = Position.builder()
                .name("New Backend Developer")
                .description("Responsible for APIs")
                .annualSalary(new BigDecimal("50000"))
                .bonusSalary(new BigDecimal("3000"))
                .numVacancies(2)
                .build();

        Position saved = positionPersistenceMongodb.create(newPosition);

        // Seeder created references from 1001 to 1004 → next must be 1005
        assertThat(saved.getReference()).isEqualTo(1005);
        assertThat(saved.getName()).isEqualTo("New Backend Developer");

        List<PositionEntity> allPositions = positionRepository.findAll();
        assertThat(allPositions).anyMatch(p -> p.getReference() == 1005 && p.getName().equals("New Backend Developer"));
    }

    @Test
    void testRead_ExistingReference_ShouldReturnPosition() {
        Position position = positionPersistenceMongodb.read(1002);

        assertThat(position.getReference()).isEqualTo(1002);
        assertThat(position.getName()).isEqualTo("CPI consultant");
    }

    @Test
    void testRead_NonExistingReference_ShouldThrow() {
        assertThrows(RuntimeException.class, () -> positionPersistenceMongodb.read(9999));
    }

    @Test
    void testUpdate_ExistingPosition_ShouldChangeFields() {
        Position updatedData = Position.builder()
                .name("Updated CPI Consultant")
                .description("Updated description")
                .annualSalary(new BigDecimal("50000"))
                .bonusSalary(new BigDecimal("5000"))
                .numVacancies(4)
                .build();

        positionPersistenceMongodb.update(1002, updatedData);

        PositionEntity entity = positionRepository.findAll().stream()
                .filter(p -> p.getReference() == 1002)
                .findFirst()
                .orElseThrow();

        assertThat(entity.getName()).isEqualTo("Updated CPI Consultant");
        assertThat(entity.getDescription()).isEqualTo("Updated description");
        assertThat(entity.getAnnualSalary()).isEqualByComparingTo(new BigDecimal("50000"));
        assertThat(entity.getBonusSalary()).isEqualByComparingTo(new BigDecimal("5000"));
        assertThat(entity.getNumVacancies()).isEqualTo(4);
    }

    @Test
    void testUpdate_NonExistingPosition_ShouldThrow() {
        Position updatedData = Position.builder()
                .name("Nonexistent")
                .build();

        assertThrows(RuntimeException.class, () -> positionPersistenceMongodb.update(9999, updatedData));
    }

    @Test
    void testFindAll_PositionsAreOrderedByReference() {
        // Read all positions from repository
        List<PositionEntity> allPositions = positionRepository.findAll();

        // Check the correct order
        assertThat(allPositions)
                .extracting(PositionEntity::getReference)
                .containsExactly(1001, 1002, 1003, 1004);
    }
}