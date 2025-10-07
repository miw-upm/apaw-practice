package es.upm.miw.apaw.adapters.mongodb.vehicle.persistence;

import es.upm.miw.apaw.adapters.mongodb.vehicle.daos.EngineRepository;
import es.upm.miw.apaw.adapters.mongodb.vehicle.daos.VehicleSeeder;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.vehicle.Engine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class EnginePersistenceMongodbIT {

    @Autowired
    private EnginePersistenceMongodb enginePersistenceMongodb;

    @Autowired
    private EngineRepository engineRepository;

    @Autowired
    private VehicleSeeder vehicleSeeder;

    @BeforeEach
    void resetDb() {
        vehicleSeeder.deleteAll();
        vehicleSeeder.seedDatabase();
    }

    @Test
    void testExistCodeEngine() {
        assertThat(this.enginePersistenceMongodb.existCodeEngine("VMIVDS000VIS00000")).isTrue();
        assertThat(this.enginePersistenceMongodb.existCodeEngine("INVALID_CODE")).isFalse();
    }

    @Test
    void testCreate() {
        Engine newEngine = Engine.builder()
                .codeEngine("VMIVDS000VIS99999")
                .type("Gasolina")
                .displacement(750.00)
                .build();

        Engine created = this.enginePersistenceMongodb.create(newEngine);

        assertThat(created).isNotNull();
        assertThat(created.getCodeEngine()).isEqualTo("VMIVDS000VIS99999");
        assertThat(this.engineRepository.findByCodeEngine("VMIVDS000VIS99999")).isPresent();
    }

    @Test
    void testUpdate() {
        Engine existing = this.engineRepository.findByCodeEngine("VMIVDS000VIS00001")
                .map(EngineEntity -> EngineEntity.toEngine())
                .orElseThrow();

        existing.setType("Híbrido");
        existing.setDisplacement(1900.00);

        Engine updated = this.enginePersistenceMongodb.update(existing);

        assertThat(updated.getType()).isEqualTo("Híbrido");
        assertThat(updated.getDisplacement()).isEqualTo(1900.00);
    }

    @Test
    void testUpdateNotFound() {
        Engine fakeEngine = Engine.builder()
                .codeEngine("NOT_EXIST")
                .type("Diesel")
                .displacement(1600.0)
                .build();

        assertThatThrownBy(() -> this.enginePersistenceMongodb.update(fakeEngine))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Code engine: NOT_EXIST not found.");
    }
}
