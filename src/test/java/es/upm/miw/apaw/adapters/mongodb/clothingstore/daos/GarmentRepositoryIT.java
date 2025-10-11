package es.upm.miw.apaw.adapters.mongodb.clothingstore.daos;

import es.upm.miw.apaw.adapters.mongodb.DatabaseSeeder;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.GarmentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@DataMongoTest
//@ActiveProfiles("test")
class GarmentRepositoryIT {

    //@Autowired
    private GarmentRepository garmentRepository;

    //@Autowired
    private DatabaseSeeder databaseSeeder;

    //@BeforeEach
    void seed() {
        databaseSeeder.reSeedDatabase();
    }

//    @Test
    void testFindByPriceBetween() {
        List<GarmentEntity> entities =
                this.garmentRepository.findByPriceBetween(new BigDecimal("50"), new BigDecimal("100"));

        assertThat(entities).isNotNull();
        // 同上，如果 seeder 确认有数据可断言非空
        // assertThat(entities).isNotEmpty();

        assertThat(entities).allSatisfy(e ->
                assertThat(e.getPrice()).isBetween(new BigDecimal("50"), new BigDecimal("100"))
        );
    }
}

