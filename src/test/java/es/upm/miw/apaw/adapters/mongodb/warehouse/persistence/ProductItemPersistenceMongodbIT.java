package es.upm.miw.apaw.adapters.mongodb.warehouse.persistence;

import es.upm.miw.apaw.domain.models.warehouse.ProductItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ProductItemPersistenceMongodbIT {

    @Autowired
    private ProductItemPersistenceMongodb productItemPersistence;

    @Test
    void testReadByBarcode() {
        Optional<ProductItem> item = this.productItemPersistence.readByBarcode("PI-001");
        assertThat(item).isPresent();
        assertThat(item.get().getAppoint()).isEqualTo("Wood Screw 10mm");
        assertThat(item.get().getCost()).isEqualByComparingTo(new BigDecimal("0.20"));
    }

}