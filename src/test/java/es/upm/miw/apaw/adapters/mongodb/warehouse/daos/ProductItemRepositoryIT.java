package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.ProductItemEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

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
        Optional<ProductItemEntity> productItem = this.productItemRepository.findByBarcode("PROD-9001");
        assertTrue(productItem.isPresent());
        ProductItemEntity entity = productItem.get();
        assertThat(entity.getAppoint()).isEqualTo("Motor hidráulico industrial");
        assertThat(entity.getCost()).isEqualByComparingTo("220.45");
    }

}
