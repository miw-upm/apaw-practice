package es.upm.miw.apaw.domain.services.clothingstore;

import es.upm.miw.apaw.adapters.mongodb.DatabaseSeeder;
import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@ActiveProfiles("test")
class GarmentServiceIT {

    @Autowired
    private GarmentService garmentService;

    @Autowired
    private DatabaseSeeder databaseSeeder;

    @BeforeEach
    void seed() {
        databaseSeeder.reSeedDatabase();
    }

    @Test
    void testFindByPriceBetween() {
        List<Garment> garments = this.garmentService
                .findByPriceBetween(new BigDecimal("50"), new BigDecimal("100"))
                .toList();

        assertThat(garments).isNotNull();
        assertThat(garments).isNotEmpty();
        assertThat(garments).allSatisfy(g ->
                assertThat(g.getPrice()).isBetween(new BigDecimal("50"), new BigDecimal("100"))
        );
    }
    @Test
    void testUpdate() {
        List<Garment> garments = this.garmentService
                .findByPriceBetween(new BigDecimal("0"), new BigDecimal("1000000"))
                .toList();
        assertThat(garments).isNotNull();
        assertThat(garments).isNotEmpty();
        Garment original = garments.get(0);
        UUID id = original.getId();

        Garment changes = new Garment();
        changes.setSize(original.getSize());
        changes.setOnSale(original.getOnSale() == null ? Boolean.TRUE : !original.getOnSale());
        changes.setPrice(original.getPrice() == null
                ? new BigDecimal("15.00")
                : original.getPrice().add(new BigDecimal("15.00")));

        Garment updated = this.garmentService.update(id, changes);
        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isEqualTo(id);
        assertThat(updated.getPrice()).isEqualByComparingTo(changes.getPrice());
        assertThat(updated.getOnSale()).isEqualTo(changes.getOnSale());
        assertThat(updated.getSize()).isEqualTo(changes.getSize());
    }
}

