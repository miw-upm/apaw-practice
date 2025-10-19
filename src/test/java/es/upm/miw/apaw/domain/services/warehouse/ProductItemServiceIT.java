package es.upm.miw.apaw.domain.services.warehouse;

import es.upm.miw.apaw.domain.models.warehouse.ProductItem;
import es.upm.miw.apaw.domain.persistenceports.warehouse.ProductItemPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ProductItemServiceIT {

    @Autowired
    private ProductItemService productItemService;

    @Autowired
    private ProductItemPersistence productItemPersistence;

    @Test
    void testUpdateProductItem() {
        String barcode = "PI-001";
        ProductItem original = this.productItemPersistence.readByBarcode(barcode)
                .orElseThrow(() -> new RuntimeException("Product not found in seeder"));

        try {
            ProductItem input = ProductItem.builder()
                    .appoint("Updated Screw 12mm")
                    .cost(new BigDecimal("0.25"))
                    .unitOfMeasure("UNIT")
                    .build();

            ProductItem updated = this.productItemService.update(barcode, input);

            assertThat(updated.getAppoint()).isEqualTo("Updated Screw 12mm");
            assertThat(updated.getCost()).isEqualByComparingTo("0.25");
        } finally {
            this.productItemService.update(barcode, original);
        }

        ProductItem restored = this.productItemPersistence.readByBarcode(barcode)
                .orElseThrow(() -> new RuntimeException("Product not restored"));
        assertThat(restored.getAppoint()).isEqualTo(original.getAppoint());
        assertThat(restored.getCost()).isEqualByComparingTo(original.getCost());
    }

}
