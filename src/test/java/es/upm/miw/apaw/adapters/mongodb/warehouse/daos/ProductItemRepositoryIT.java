package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.ProductItemEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ProductItemRepositoryIT {

    @Autowired
    private ProductItemRepository productItemRepository;

    @Test
    void testFindByBarcode() {
        Optional<ProductItemEntity> optionalItem = this.productItemRepository.findByBarcode("PI-001");
        assertThat(optionalItem).isPresent();

        ProductItemEntity item = optionalItem.get();
        assertThat(item.getBarcode()).isEqualTo("PI-001");
        assertThat(item.getAppoint()).isNotBlank();
        assertThat(item.getCost()).isNotNull();
        assertThat(item.getUnitOfMeasure()).isNotBlank();
    }

}