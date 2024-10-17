package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.ProductECommerce;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestConfig
public class ProductECommercePersistenceMongodbIT {

    @Autowired
    private ProductECommercePersistenceMongodb productECommercePersistenceMongodb;

    @Test
    void testCreateAndRead() {
        ProductECommerce product = new ProductECommerce( "Laptop", 10, BigDecimal.valueOf(1200.00));
        ProductECommerce productSaved = this.productECommercePersistenceMongodb.create(product);
        assertNotNull(productSaved);
        assertEquals("Laptop", productSaved.getProductName());
        assertEquals(10, productSaved.getNumberProduct());
        assertEquals(BigDecimal.valueOf(1200.00), productSaved.getUnitPrice());
    }

    @Test
    void testCreateAndReadWithDifferentPrice() {
        ProductECommerce product = new ProductECommerce( "Laptop", 10, BigDecimal.valueOf(1300.00));
        ProductECommerce productSaved = this.productECommercePersistenceMongodb.create(product);
        assertNotNull(productSaved);
        assertEquals("Laptop", productSaved.getProductName());
        assertEquals(10, productSaved.getNumberProduct());
        assertEquals(BigDecimal.valueOf(1300.00), productSaved.getUnitPrice());
    }
}
