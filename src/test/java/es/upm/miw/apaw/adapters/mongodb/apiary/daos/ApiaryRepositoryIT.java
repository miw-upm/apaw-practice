package es.upm.miw.apaw.adapters.mongodb.apiary.daos;

import es.upm.miw.apaw.adapters.mongodb.apiary.entities.ApiaryEntity;
import es.upm.miw.apaw.adapters.mongodb.apiary.entities.HiveEntity;
import es.upm.miw.apaw.adapters.mongodb.apiary.entities.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataMongoTest
@ActiveProfiles("test")
class ApiaryRepositoryIT {

    @Autowired
    private ApiaryRepository apiaryRepository;

    @Test
    void testFindByLocationReturnsApiaries() {
        ApiaryEntity entity = ApiaryEntity.builder()
                .id(UUID.randomUUID())
                .cadastralRef("0000000-00000000-0001-XX")
                .location("Burgos")
                .rega("REGA00001")
                .build();
        apiaryRepository.save(entity);

        List<ApiaryEntity> apiaries = apiaryRepository.findByLocation("Burgos");
        assertThat(apiaries)
                .isNotNull()
                .isNotEmpty()
                .allMatch(apiary -> "Burgos".equals(apiary.getLocation()));
    }

    @Test
    void testFindByLocationReturnsEmptyListForUnknownLocation() {
        List<ApiaryEntity> apiaries = apiaryRepository.findByLocation("UnknownLocation");
        assertThat(apiaries).isNotNull().isEmpty();
    }

    @Test
    void testFindByLocationCaseSensitivity() {
        List<ApiaryEntity> apiaries = apiaryRepository.findByLocation("burgos");
        assertThat(apiaries).isEmpty();
    }

    @Test
    void testToApiaryCoversIfBranches() {
        ApiaryEntity entityNull = ApiaryEntity.builder()
                .cadastralRef("test-null")
                .location("loc")
                .rega("rega")
                .hiveEntities(null)
                .build();
        assertNull(entityNull.toApiary().getHives());

        HiveEntity hive = HiveEntity.builder()
                .code(1)
                .build();
        ApiaryEntity entityNotNull = ApiaryEntity.builder()
                .cadastralRef("test-notnull")
                .location("loc")
                .rega("rega")
                .hiveEntities(List.of(hive))
                .build();
        assertNotNull(entityNotNull.toApiary().getHives());
        assertEquals(1, entityNotNull.toApiary().getHives().size());
    }

    @Test
    void testToHiveCoversIfBranches() {
        HiveEntity hiveNull = HiveEntity.builder()
                .code(1)
                .productEntity(null)
                .build();
        assertNull(hiveNull.toHive().getProduct());

        ProductEntity product = ProductEntity.builder()
                .barcode("X")
                .build();
        HiveEntity hiveNotNull = HiveEntity.builder()
                .code(2)
                .productEntity(product)
                .build();
        assertNotNull(hiveNotNull.toHive().getProduct());
        assertEquals("X", hiveNotNull.toHive().getProduct().getBarcode());
    }
}