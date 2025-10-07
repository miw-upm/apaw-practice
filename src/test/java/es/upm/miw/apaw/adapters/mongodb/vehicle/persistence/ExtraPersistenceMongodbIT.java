package es.upm.miw.apaw.adapters.mongodb.vehicle.persistence;

import es.upm.miw.apaw.adapters.mongodb.vehicle.daos.ExtraRepository;
import es.upm.miw.apaw.adapters.mongodb.vehicle.daos.VehicleSeeder;
import es.upm.miw.apaw.adapters.mongodb.vehicle.entities.ExtraEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.vehicle.Extra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class ExtraPersistenceMongodbIT {

    @Autowired
    private ExtraPersistenceMongodb extraPersistenceMongodb;

    @Autowired
    private ExtraRepository extraRepository;

    @Autowired
    private VehicleSeeder vehicleSeeder;

    @BeforeEach
    void resetDb() {
        vehicleSeeder.deleteAll();
        vehicleSeeder.seedDatabase();
    }

    @Test
    void testReadByCategoryAndDescriptionFound() {
        Extra extra = this.extraPersistenceMongodb.readByCategoryAndDescription("Safety", "Automatic Emergency Braking (AEB)");
        assertThat(extra.getCategory()).isEqualTo("Safety");
        assertThat(extra.getDescription()).isEqualTo("Automatic Emergency Braking (AEB)");
        assertThat(extra.getPrice()).isPositive();
    }

    @Test
    void testReadByCategoryAndDescriptionNotFound() {
        assertThatThrownBy(() -> this.extraPersistenceMongodb.readByCategoryAndDescription("Fake", "No existe"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Extra not found with category: Fake and description: No existe");
    }

    @Test
    void testUpdate() {
        Extra extra = this.extraPersistenceMongodb.readByCategoryAndDescription("Safety", "Automatic Emergency Braking (AEB)");
        extra.setPrice(new BigDecimal("199.99"));

        Extra updated = this.extraPersistenceMongodb.update(extra);

        assertThat(updated.getPrice()).isEqualByComparingTo("199.99");
    }

    @Test
    void testUpdateNotFound() {
        Extra fake = Extra.builder()
                .category("FakeCat")
                .description("FakeDesc")
                .price(new BigDecimal("10.00"))
                .build();

        assertThatThrownBy(() -> this.extraPersistenceMongodb.update(fake))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Extra not found");
    }

    @Test
    void testDelete() {
        assertThat(this.extraPersistenceMongodb.readByCategoryAndDescription("Safety", "Automatic Emergency Braking (AEB)")).isNotNull();
        this.extraPersistenceMongodb.delete(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"));
        assertThatThrownBy(() -> this.extraPersistenceMongodb.readByCategoryAndDescription("Safety", "Automatic Emergency Braking (AEB)"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Extra not found with category: Safety and description: Automatic Emergency Braking (AEB)");
    }
}
