package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.ProductItemEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class ProductItemRepositoryIT {

    @Autowired
    private ProductItemRepository productItemRepository;

    @Test
    void testFindAll() {
        assertThat(this.productItemRepository.findAll()).isNotEmpty();
    }

    @Test
    void testFindByBarcode() {
        assertTrue(this.productItemRepository.findByBarcode("PI-003").isPresent());
        ProductItemEntity productItem = this.productItemRepository.findByBarcode("PI-003").get();
        assertThat(productItem.getAppoint()).isEqualTo("Plastic Handle");
        assertThat(productItem.getCost()).isEqualByComparingTo("1.15");
        assertThat(productItem.getUnitOfMeasure()).isEqualTo("UNIT");
    }

}
