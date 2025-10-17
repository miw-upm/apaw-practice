
package es.upm.miw.apaw.domain.services.clothingstore;

import es.upm.miw.apaw.adapters.mongodb.DatabaseSeeder;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.GarmentRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class GarmentServiceIT {

    @Autowired
    private GarmentService garmentService;

    @Autowired
    private GarmentRepository garmentRepository;

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

        assertThat(garments).isNotNull().isNotEmpty();
        assertThat(garments).allSatisfy(g ->
                assertThat(g.getPrice()).isBetween(new BigDecimal("50"), new BigDecimal("100"))
        );
    }

    @Test
    void testUpdate() {
        List<Garment> garments = this.garmentService
                .findByPriceBetween(new BigDecimal("0"), new BigDecimal("1000000"))
                .toList();
        assertThat(garments).isNotNull().isNotEmpty();

        Garment original = garments.getFirst();
        UUID id = original.getId();

        Garment changes = Garment.builder()
                .size(original.getSize())
                .onSale(original.getOnSale() == null ? Boolean.TRUE : !original.getOnSale())
                .price(original.getPrice() == null
                        ? new BigDecimal("15.00")
                        : original.getPrice().add(new BigDecimal("15.00")))
                .build();

        Garment updated = this.garmentService.update(id, changes);

        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isEqualTo(id);
        assertThat(updated.getPrice()).isEqualByComparingTo(changes.getPrice());
        assertThat(updated.getOnSale()).isEqualTo(changes.getOnSale());
        assertThat(updated.getSize()).isEqualTo(changes.getSize());
    }

    @Test
    void testCreate() {
        Garment garment = Garment.builder()
                .size("S")
                .price(new BigDecimal("19.99"))
                .onSale(false)
                .build();

        Garment created = garmentService.create(garment);

        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();
        assertThat(created.getSize()).isEqualTo("S");
        assertThat(created.getPrice()).isEqualByComparingTo("19.99");
        assertThat(created.getOnSale()).isFalse();

        List<Garment> inRange = this.garmentService
                .findByPriceBetween(new BigDecimal("0"), new BigDecimal("20"))
                .toList();
        assertThat(inRange.stream().anyMatch(g -> g.getId().equals(created.getId()))).isTrue();
    }

    @Test
    void testDelete_ok() {
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7001"); // 种子里的一件衣服
        assertThat(garmentRepository.findById(id)).isPresent();

        garmentService.delete(id);

        assertThat(garmentRepository.findById(id)).isEmpty();
    }

    @Test
    void testDelete_notFound() {
        UUID unknown = UUID.fromString("ffffffff-ffff-ffff-ffff-ffffffff9999");
        assertThat(garmentRepository.findById(unknown)).isEmpty();

        assertThatThrownBy(() -> garmentService.delete(unknown))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Garment not found");
    }

}

