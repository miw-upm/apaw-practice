package es.upm.miw.apaw.adapters.mongodb.vehicle.persistence;

import es.upm.miw.apaw.adapters.mongodb.vehicle.daos.VehicleSeeder;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.vehicle.Vehicle;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("test")
class VehiclePersistenceMongodbIT {

    @Autowired
    private VehiclePersistenceMongodb vehiclePersistenceMongodb;

    @Autowired
    private VehicleSeeder vehicleSeeder;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testReadByBrand() {
        List<Vehicle> vehicles = this.vehiclePersistenceMongodb.readByBrand("Peugeot").toList();

        assertThat(vehicles)
                .isNotEmpty()
                .allSatisfy(vehicle -> {
                    assertThat(vehicle.getBrand()).isEqualTo("Peugeot");
                    assertThat(vehicle.getPlate()).startsWith("000");
                    assertThat(vehicle.getEngine()).isNotNull();
                    assertThat(vehicle.getDocumentations()).isNotEmpty();
                });
    }

    @Test
    void testReadByBrand_NoResults() {
        List<Vehicle> vehicles = this.vehiclePersistenceMongodb.readByBrand("Ferrari").toList();
        assertThat(vehicles).isEmpty();
    }

    @Test
    void testSeedReload() {
        vehicleSeeder.deleteAll();
        vehicleSeeder.seedDatabase();

        List<Vehicle> vehicles = this.vehiclePersistenceMongodb.readByBrand("Honda").toList();
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

    @Test
    void testFindUserMobilesByEngineType() {
        // It is important to simulate userRestClient.
        // The first test I did was with apaw-user running on local and they executed successfully, but GitHub Actions cannot access the apaw-user URL.
        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation ->
                    UserDto.builder().id(invocation.getArgument(0)).mobile("123456789").firstName("mock").build());

        List<String> mobiles = this.vehiclePersistenceMongodb.findUserMobilesByEngineType("Diesel");
        assertThat(mobiles)
                .isNotEmpty()
                .containsExactly("123456789")
                .doesNotHaveDuplicates();
    }
}
