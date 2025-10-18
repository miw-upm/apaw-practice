package es.upm.miw.apaw.adapters.mongodb.clothingstore.daos;

import es.upm.miw.apaw.adapters.mongodb.DatabaseSeeder;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.GarmentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import es.upm.miw.apaw.domain.services.clothingstore.GarmentService;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class GarmentRepositoryIT {

    @Autowired
    private GarmentRepository garmentRepository;
    @Autowired
    private GarmentService garmentService;
    @Autowired
    private clothingstoreSeeder clothingstoreSeeder;

    @BeforeEach
    void resetDb() {
        clothingstoreSeeder.deleteAll();
        clothingstoreSeeder.seedDatabase();
    }

    @Test
    void testFindById() {
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7001");
        assertTrue(garmentRepository.findById(id).isPresent());

        GarmentEntity g = garmentRepository.findById(id).get();
        assertThat(g.getSize()).isEqualTo("M");
        assertThat(g.getPrice()).isEqualByComparingTo(new BigDecimal("59.99"));
        assertThat(g.getOnSale()).isTrue();
    }

    @Test
    void testSaveNewEntity() {
        GarmentEntity newGarment = GarmentEntity.builder()
                .id(UUID.randomUUID())
                .size("S")
                .price(new BigDecimal("19.99"))
                .onSale(false)
                .build();

        GarmentEntity saved = garmentRepository.save(newGarment);

        Optional<GarmentEntity> reloaded = garmentRepository.findById(saved.getId());
        assertThat(reloaded).isPresent();
        assertThat(reloaded.get().getSize()).isEqualTo("S");
        assertThat(reloaded.get().getPrice()).isEqualByComparingTo("19.99");
        assertThat(reloaded.get().getOnSale()).isFalse();
    }

    @Test
    void testUpdateExistingEntity() {
        GarmentEntity garment = garmentRepository.findAll().get(0);

        BigDecimal newPrice = garment.getPrice().add(new BigDecimal("5.00"));
        boolean newOnSale = garment.getOnSale() == null ? true : !garment.getOnSale();

        garment.setPrice(newPrice);
        garment.setOnSale(newOnSale);
        garment.setSize("XL");
//1
        garmentRepository.save(garment);

        Optional<GarmentEntity> reloaded = garmentRepository.findById(garment.getId());
        assertThat(reloaded).isPresent();
        assertThat(reloaded.get().getPrice()).isEqualByComparingTo(newPrice);
        assertThat(reloaded.get().getOnSale()).isEqualTo(newOnSale);
        assertThat(reloaded.get().getSize()).isEqualTo("XL");
    }
    @Test
    void testDelete_ok() {
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7001");
        assertThat(garmentRepository.findById(id)).isPresent();
        garmentService.delete(id);
        assertThat(garmentRepository.findById(id)).isEmpty();
    }
}



