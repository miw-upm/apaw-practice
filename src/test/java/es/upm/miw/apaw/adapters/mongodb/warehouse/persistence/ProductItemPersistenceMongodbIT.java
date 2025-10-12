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
        List<ProductItem> items = this.productItemPersistence.readAll().toList();
        assertThat(items).isNotEmpty();
    }

    @Test
    void testReadById() {
        UUID id = UUID.fromString("bbbb2222-3333-4444-5555-666677770001");
        ProductItem productItem = this.productItemPersistence.read(id);
        assertThat(productItem.getBarcode()).isEqualTo("PROD-9001");
        assertThat(productItem.getAppoint()).contains("Motor hidráulico");
    }

    @Test
    void testCreateAndDelete() {
        ProductItem item = ProductItem.builder()
                .id(UUID.randomUUID())
                .barcode("TEST-ITEM")
                .appoint("Pieza de prueba")
                .cost(new BigDecimal("50.00"))
                .unitOfMeasure("unit")
                .build();

        ProductItem created = this.productItemPersistence.create(item);
        assertThat(created.getBarcode()).isEqualTo("TEST-ITEM");

        this.productItemPersistence.delete(created.getId());
    }

    @Test
    void testUpdate() {
        UUID id = UUID.fromString("bbbb2222-3333-4444-5555-666677770002");
        ProductItem item = this.productItemPersistence.read(id);
        item.setCost(new BigDecimal("199.99"));

        ProductItem updated = this.productItemPersistence.update(id, item);
        assertThat(updated.getCost()).isEqualByComparingTo(new BigDecimal("199.99"));
    }

}
