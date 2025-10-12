package es.upm.miw.apaw.adapters.mongodb.recruiting.persistence;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.RecruitingSeeder;
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
    private PositionPersistenceMongodb positionPersistence;

    @Autowired
    private RecruitingSeeder recruitingSeeder;

    @BeforeEach
    void resetDb() {
        recruitingSeeder.deleteAll();
        recruitingSeeder.seedDatabase();
    }

    @Test
    void testCreatePosition() {
        Position newPosition = Position.builder()
                .name("New Backend Developer")
                .description("Responsible for APIs")
                .annualSalary(new BigDecimal("50000"))
                .bonusSalary(new BigDecimal("3000"))
                .numVacancies(2)
                .build();

        Position saved = positionPersistence.create(newPosition);

        // Seeder created references from 1001 to 1005 → next must be 1006
        assertThat(saved.getReference()).isEqualTo(1006);
        assertThat(saved.getName()).isEqualTo("New Backend Developer");

        List<Position> allPositions = positionPersistence.readAll();
        assertThat(allPositions).anyMatch(p -> p.getReference() == 1006 && p.getName().equals("New Backend Developer"));
    }

    @Test
    void testReadPositionByReference() {
        Position position = positionPersistence.read(1001);

        assertThat(position.getReference()).isEqualTo(1001);
        assertThat(position.getName()).isEqualTo("ABAP developer");
    }

    @Test
    void testReadNonExistingReference() {
        assertThrows(RuntimeException.class, () -> positionPersistence.read(9999));
    }

    @Test
    void testUpdateAndRead() {
        Position updatePosition = Position.builder()
                .name("Updated CPI Consultant")
                .description("Updated description")
                .annualSalary(new BigDecimal("50000"))
                .bonusSalary(new BigDecimal("5000"))
                .numVacancies(4)
                .build();

        positionPersistence.update(1002, updatePosition);

        Position readPosition = positionPersistence.read(1002);

        assertThat(readPosition.getName()).isEqualTo("Updated CPI Consultant");
        assertThat(readPosition.getDescription()).isEqualTo("Updated description");
        assertThat(readPosition.getAnnualSalary()).isEqualByComparingTo(new BigDecimal("50000"));
        assertThat(readPosition.getBonusSalary()).isEqualByComparingTo(new BigDecimal("5000"));
        assertThat(readPosition.getNumVacancies()).isEqualTo(4);
    }
}