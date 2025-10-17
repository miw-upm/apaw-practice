package es.upm.miw.apaw.adapters.mongodb.apiary.persistence;

import es.upm.miw.apaw.adapters.mongodb.apiary.daos.ApiaryRepository;
import es.upm.miw.apaw.adapters.mongodb.apiary.entities.ApiaryEntity;
import es.upm.miw.apaw.adapters.mongodb.apiary.entities.HiveEntity;
import es.upm.miw.apaw.adapters.mongodb.apiary.entities.ProductEntity;
import es.upm.miw.apaw.domain.models.apiary.Apiary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ApiaryPersistenceMongodbIT {

    @Autowired
    private ApiaryPersistenceMongodb apiaryPersistenceMongodb;

    @Autowired
    private ApiaryRepository apiaryRepository;

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
    void testFindLocationsByShippingAddress_hiveProductEntityNull_noMatch() {
        ApiaryEntity apiaryEntity = ApiaryEntity.builder()
                .id(UUID.randomUUID())
                .cadastralRef("NO_PRODUCT_REF")
                .location("TestNull")
                .rega("REGA_TEST_NULL")
                .hiveEntities(List.of(HiveEntity.builder().productEntity(null).build()))
                .build();
        apiaryRepository.save(apiaryEntity);

        Set<String> locations = apiaryPersistenceMongodb.findLocationsByShippingAddress("Calle Mayor 10, Madrid");
        assertThat(locations).doesNotContain("TestNull");
    }

    @Test
    void testFindLocationsByShippingAddress_hiveProductEntityBarcodeNotInSales_noMatch() {
        ProductEntity product = ProductEntity.builder()
                .id(UUID.randomUUID())
                .barcode("NO_MATCH_BARCODE")
                .build();
        HiveEntity hive = HiveEntity.builder()
                .id(UUID.randomUUID())
                .productEntity(product)
                .build();
        ApiaryEntity apiaryEntity = ApiaryEntity.builder()
                .id(UUID.randomUUID())
                .cadastralRef("NO_MATCH_REF")
                .location("TestNotMatch")
                .rega("REGA_TEST_NOMATCH")
                .hiveEntities(List.of(hive))
                .build();
        apiaryRepository.save(apiaryEntity);

        Set<String> locations = apiaryPersistenceMongodb.findLocationsByShippingAddress("Calle Mayor 10, Madrid");
        assertThat(locations).doesNotContain("TestNotMatch");
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