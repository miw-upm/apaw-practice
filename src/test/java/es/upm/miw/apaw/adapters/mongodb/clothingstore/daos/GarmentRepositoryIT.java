package es.upm.miw.apaw.adapters.mongodb.clothingstore.daos;

import es.upm.miw.apaw.adapters.mongodb.DatabaseSeeder;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.GarmentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.math.BigDecimal;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class GarmentRepositoryIT {
    @Autowired GarmentRepository garmentRepository;
    @Autowired DatabaseSeeder databaseSeeder;
    @BeforeEach void setUp(){databaseSeeder.reSeedDatabase();}
    @Test void testFindById(){
        UUID id=UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7001");
        assertTrue(garmentRepository.findById(id).isPresent());
        GarmentEntity g=garmentRepository.findById(id).get();
        assertThat(g.getSize()).isEqualTo("M");
        assertThat(g.getPrice()).isEqualByComparingTo(new BigDecimal("59.99"));
        assertThat(g.getOnSale()).isTrue();
    }
}


