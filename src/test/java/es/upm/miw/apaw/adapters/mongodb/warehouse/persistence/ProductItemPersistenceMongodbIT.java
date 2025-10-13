package es.upm.miw.apaw.adapters.mongodb.warehouse.persistence;

import es.upm.miw.apaw.domain.models.warehouse.ProductItem;
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
public class ProductItemPersistenceMongodbIT {

    @Autowired
    private ProductItemPersistenceMongodb productItemPersistence;

    @Test
    void testReadAll() {
        List<ProductItem> productItems = this.productItemPersistence.readAll().toList();
        assertThat(productItems).isNotEmpty();
        assertThat(productItems)
                .extracting(ProductItem::getBarcode)
                .contains("PI-001", "PI-002", "PI-003");
    }

    @Test
    void testReadByBarcode() {
        ProductItem productItem = this.productItemPersistence.read("PI-002");
        assertThat(productItem.getBarcode()).isEqualTo("PI-002");
        assertThat(productItem.getAppoint()).isEqualTo("Metal Bolt 15mm");
    }

    @Test
    void testCreateAndUpdate() {
        ProductItem newItem = ProductItem.builder()
                .barcode("PI-999")
                .appoint("Plastic Tube")
                .cost(new BigDecimal("5.45"))
                .unitOfMeasure("UNIT")
                .build();

        ProductItem created = this.productItemPersistence.create(newItem);
        assertThat(created.getBarcode()).isEqualTo("PI-999");

        // Actualización
        created.setCost(new BigDecimal("6.50"));
        ProductItem updated = this.productItemPersistence.update("PI-999", created);
        assertThat(updated.getCost()).isEqualByComparingTo("6.50");
    }

}
