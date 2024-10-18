package es.upm.miw.apaw_practice.adapters.rest.e_commerce;
import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.ProductECommerce;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.persistence.ProductECommercePersistenceMongodb;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@TestConfig
public class ProductECommerceResourceIT {

    @Autowired
    private ProductECommercePersistenceMongodb productPersistenceMongodb;

    @Test
    void testCreateAndRead() {
        ProductECommerce product = new ProductECommerce( "Laptop", 10, BigDecimal.valueOf(1200.50));
        ProductECommerce productSaved = this.productPersistenceMongodb.create(product);
        assertNotNull(productSaved);
        assertEquals("Laptop", productSaved.getProductName());
        assertEquals(10, productSaved.getNumberProduct());
        assertEquals(BigDecimal.valueOf(1200.50), productSaved.getUnitPrice());
    }

    @Test
    void testCreateAndReadDifferentProduct() {
        ProductECommerce product = new ProductECommerce( "Smartphone", 25, BigDecimal.valueOf(750.00));
        ProductECommerce productSaved = this.productPersistenceMongodb.create(product);
        assertNotNull(productSaved);
        assertEquals("Smartphone", productSaved.getProductName());
        assertEquals(25, productSaved.getNumberProduct());
        assertEquals(BigDecimal.valueOf(750.00), productSaved.getUnitPrice());
    }
}
