package es.upm.miw.apaw.adapters.mongodb.vehicle.daos;

import es.upm.miw.apaw.adapters.mongodb.vehicle.entities.VehicleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class VehicleRepositoryIT {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    void testFindAllAndRead() {
        assertThat(this.vehicleRepository.findAll())
                .isNotEmpty()
                .anySatisfy(vehicle -> {
                    assertThat(vehicle.getPlate()).isNotNull();
                    assertThat(vehicle.getBrand()).isIn("Peugeot", "Honda", "BMW", "Benelli", "Yamaha");
                    assertThat(vehicle.getModel()).isNotEmpty();
                    assertThat(vehicle.getRegistrationDate()).isBeforeOrEqualTo(LocalDate.now());
                    assertThat(vehicle.getEngineEntity()).isNotNull();
                    assertThat(vehicle.getDocumentationEntities()).isNotEmpty();
                });

        VehicleEntity vehicle = this.vehicleRepository.findAll().getFirst();
        assertThat(vehicle.getId()).isNotNull();
        assertThat(vehicle.getOwner()).isInstanceOf(UUID.class);
    }
}
