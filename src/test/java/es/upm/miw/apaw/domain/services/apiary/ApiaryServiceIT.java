package es.upm.miw.apaw.domain.services.apiary;

import es.upm.miw.apaw.adapters.mongodb.apiary.daos.ApiarySeeder;
import es.upm.miw.apaw.domain.models.apiary.Apiary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ApiaryServiceIT {

    @Autowired
    private ApiaryService apiaryService;

    @Autowired
    private ApiarySeeder apiarySeeder;

    @BeforeEach
    void setUp() {
        apiarySeeder.deleteAll();
        apiarySeeder.seedDatabase();
    }


    @Test
    void testFindByLocation_returnsApiariesWithCorrectLocation() {
        List<Apiary> apiaries = apiaryService.findByLocation("Burgos").collect(Collectors.toList());
        assertThat(apiaries)
                .isNotEmpty()
                .allMatch(apiary -> "Burgos".equals(apiary.getLocation()));
    }

    @Test
    void testFindByLocation_returnsEmptyWhenNotFound() {
        List<Apiary> apiaries = apiaryService.findByLocation("NonExistentLocation").collect(Collectors.toList());
        assertThat(apiaries).isEmpty();
    }

    @Test
    void testFindLocationsByShippingAddress_returnsCorrectLocations() {
        Set<String> locations = apiaryService.findLocationsByShippingAddress("Av. Andalucía 25, Sevilla");
        assertThat(locations)
                .isNotEmpty()
                .contains("Burgos");
    }

    @Test
    void testFindLocationsByShippingAddress_returnsEmptyWhenNotFound() {
        Set<String> locations = apiaryService.findLocationsByShippingAddress("Fake Address 123");
        assertThat(locations).isEmpty();
    }
}