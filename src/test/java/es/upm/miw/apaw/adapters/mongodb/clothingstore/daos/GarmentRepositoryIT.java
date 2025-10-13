package es.upm.miw.apaw.adapters.mongodb.clothingstore.daos;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.GarmentEntity;
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

    @Autowired
    private GarmentRepository garmentRepository;

    @Test
    void testFindById() {

        UUID garmentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7001");

        assertTrue(this.garmentRepository.findById(garmentId).isPresent());

        GarmentEntity garment = this.garmentRepository.findById(garmentId).get();
        assertThat(garment.getSize()).isEqualTo("M");
        assertThat(garment.getPrice()).isEqualByComparingTo(new BigDecimal("59.99"));
        assertThat(garment.getOnSale()).isTrue();
    }
}
