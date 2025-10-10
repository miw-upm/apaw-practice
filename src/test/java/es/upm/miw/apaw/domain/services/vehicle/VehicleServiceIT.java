package es.upm.miw.apaw.domain.services.vehicle;

import es.upm.miw.apaw.domain.models.vehicle.Vehicle;
import es.upm.miw.apaw.domain.persistenceports.vehicle.VehiclePersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class VehicleServiceIT {

    @Autowired
    private VehicleService vehicleService;

    @MockitoBean
    private VehiclePersistence vehiclePersistence;

    @Test
    void testFindByBrand() {
        Vehicle v1 = Vehicle.builder()
                .plate("0001FGG")
                .brand("Peugeot")
                .model("407 sw")
                .registrationDate(LocalDate.of(2006, 1, 1))
                .build();

        Vehicle v2 = Vehicle.builder()
                .plate("0002FGG")
                .brand("Peugeot")
                .model("308")
                .registrationDate(LocalDate.of(2010, 1, 1))
                .build();

        BDDMockito.given(this.vehiclePersistence.readByBrand("Peugeot"))
                .willReturn(Stream.of(v1, v2));

        List<Vehicle> result = this.vehicleService.findByBrand("Peugeot").toList();

        assertThat(result)
                .hasSize(2)
                .allSatisfy(vehicle -> {
                    assertThat(vehicle.getBrand()).isEqualTo("Peugeot");
                    assertThat(vehicle.getPlate()).startsWith("000");
                    assertThat(vehicle.getRegistrationDate()).isBeforeOrEqualTo(LocalDate.now());
                });
    }

    @Test
    void testFindExtraCategoriesByDocumentationName() {
        BDDMockito.given(this.vehiclePersistence.findExtraCategoriesByDocumentationName("ITV"))
                .willReturn(List.of("Safety"));
        List<String> categories = this.vehicleService.findExtraCategoriesByDocumentationName("ITV");

        assertThat(categories)
                .isNotEmpty()
                .containsExactly("Safety")
                .doesNotHaveDuplicates();
    }

    @Test
    void testFindUserMobilesByEngineType() {
        BDDMockito.given(this.vehiclePersistence.findUserMobilesByEngineType("Diesel"))
                .willReturn(List.of("666000666"));
        List<String> mobiles = this.vehicleService.findUserMobilesByEngineType("Diesel");

        assertThat(mobiles)
                .isNotEmpty()
                .containsExactly("666000666")
                .doesNotHaveDuplicates();
    }
}
