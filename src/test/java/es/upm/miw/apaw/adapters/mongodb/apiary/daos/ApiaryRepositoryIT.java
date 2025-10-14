package es.upm.miw.apaw.adapters.mongodb.apiary.daos;

import es.upm.miw.apaw.adapters.mongodb.apiary.entities.ApiaryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

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
        // As MongoRepository default queries are case sensitive, expect empty.
        assertThat(apiaries).isEmpty();
    }
}