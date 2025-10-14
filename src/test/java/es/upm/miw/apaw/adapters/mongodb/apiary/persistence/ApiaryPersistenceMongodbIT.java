package es.upm.miw.apaw.adapters.mongodb.apiary.persistence;

import es.upm.miw.apaw.domain.models.apiary.Apiary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ApiaryPersistenceMongodbIT {

    @Autowired
    private ApiaryPersistenceMongodb apiaryPersistenceMongodb;

    @Test
    void testFindByLocation_returnsApiaries() {
        List<Apiary> apiaries = apiaryPersistenceMongodb.findByLocation("Burgos").collect(Collectors.toList());
        assertThat(apiaries)
                .isNotEmpty()
                .allMatch(apiary -> "Burgos".equals(apiary.getLocation()));
    }

    @Test
    void testFindByLocation_returnsEmptyWhenNotFound() {
        List<Apiary> apiaries = apiaryPersistenceMongodb.findByLocation("NonExistentLocation").collect(Collectors.toList());
        assertThat(apiaries).isEmpty();
    }

    @Test
    void testFindLocationsByShippingAddress_returnsCorrectLocations() {
        Set<String> locations = apiaryPersistenceMongodb.findLocationsByShippingAddress("Calle Mayor 10, Madrid");
        assertThat(locations)
                .isNotEmpty()
                .contains("Burgos");
    }

    @Test
    void testFindLocationsByShippingAddress_returnsEmptyWhenNotFound() {
        Set<String> locations = apiaryPersistenceMongodb.findLocationsByShippingAddress("Direccion Falsa 123");
        assertThat(locations).isEmpty();
    }

    @Test
    void testSumProductPricesByRega_ReturnsCorrectSum() {
        BigDecimal sum = apiaryPersistenceMongodb.sumProductPricesByRega("REGA00001");
        assertThat(sum).isEqualByComparingTo(new BigDecimal("15.00"));
    }

    @Test
    void testSumProductPricesByRega_ReturnsZeroWhenNotFound() {
        BigDecimal sum = apiaryPersistenceMongodb.sumProductPricesByRega("REGA_NO_EXISTE");
        assertThat(sum).isEqualByComparingTo(BigDecimal.ZERO);
    }

}