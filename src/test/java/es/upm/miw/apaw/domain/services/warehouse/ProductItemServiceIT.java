package es.upm.miw.apaw.domain.services.warehouse;

import es.upm.miw.apaw.domain.models.warehouse.ProductItem;
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

    @Test
    void testUpdateProductItem() {
        ProductItem updated = ProductItem.builder()
                .appoint("Updated Screw 12mm")
                .cost(new BigDecimal("0.25"))
                .unitOfMeasure("UNIT")
                .build();

        ProductItem result = this.productItemService.update("PI-001", updated);

        assertThat(result.getAppoint()).isEqualTo("Updated Screw 12mm");
        assertThat(result.getCost()).isEqualByComparingTo("0.25");
    }

}
