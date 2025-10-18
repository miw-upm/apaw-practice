package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.ProductItemEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class ProductItemRepositoryIT {

    @Autowired
    private ProductItemRepository productItemRepository;

    //@Test
    void testFindByBarcode() {
        assertTrue(this.productItemRepository.findByBarcode("PI-001").isPresent());
        ProductItemEntity item = this.productItemRepository.findByBarcode("PI-001").get();
        assertThat(item.getAppoint()).isEqualTo("Wood Screw 10mm");
        assertThat(item.getCost()).isEqualByComparingTo(new BigDecimal("0.20"));
        assertThat(item.getUnitOfMeasure()).isEqualTo("UNIT");
    }

}