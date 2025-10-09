package es.upm.miw.apaw.adapters.mongodb.vehicle.persistence;

import es.upm.miw.apaw.adapters.mongodb.vehicle.daos.VehicleSeeder;
import es.upm.miw.apaw.domain.models.vehicle.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class VehiclePersistenceMongodbIT {

    @Autowired
    private VehiclePersistenceMongodb vehiclePersistenceMongodb;

    @Autowired
    private VehicleSeeder vehicleSeeder;

    @Test
    void testReadByBrand() {
        List<Vehicle> vehicles = this.vehiclePersistenceMongodb.readByBrand("Peugeot").collect(Collectors.toList());

        assertThat(vehicles).isNotEmpty();
        assertThat(vehicles)
                .allSatisfy(vehicle -> {
                    assertThat(vehicle.getBrand()).isEqualTo("Peugeot");
                    assertThat(vehicle.getPlate()).startsWith("000");
                    assertThat(vehicle.getEngine()).isNotNull();
                    assertThat(vehicle.getDocumentations()).isNotEmpty();
                });
    }

    @Test
    void testReadByBrand_NoResults() {
        List<Vehicle> vehicles = this.vehiclePersistenceMongodb.readByBrand("Ferrari").collect(Collectors.toList());
        assertThat(vehicles).isEmpty();
    }

    @Test
    void testSeedReload() {
        vehicleSeeder.deleteAll();
        vehicleSeeder.seedDatabase();

        List<Vehicle> vehicles = this.vehiclePersistenceMongodb.readByBrand("Honda").collect(Collectors.toList());
        assertThat(vehicles).isNotEmpty();
    }

    @Test
    void testFindExtraCategoriesByDocumentationName() {
        List<String> categories = this.vehiclePersistenceMongodb.findExtraCategoriesByDocumentationName("ITV");

        assertThat(categories)
                .isNotEmpty()
                .containsExactly("Safety", "Comfort", "Appearance")
                .doesNotHaveDuplicates();

        List<String> categories2 = this.vehiclePersistenceMongodb.findExtraCategoriesByDocumentationName("Insurance policy");

        assertThat(categories2)
                .isNotEmpty()
                .containsExactly("Safety")
                .doesNotHaveDuplicates();
    }
}
