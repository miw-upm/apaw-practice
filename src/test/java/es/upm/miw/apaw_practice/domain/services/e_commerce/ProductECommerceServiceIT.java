package es.upm.miw.apaw_practice.domain.services.e_commerce;
import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.ProductECommerce;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@TestConfig
public class ProductECommerceServiceIT {

    @Autowired
    private ProductECommerceService productECommerceService;

    @Test
    void testCreateAndRead() {
        ProductECommerce product = new ProductECommerce( "Laptop", 10, BigDecimal.valueOf(1200.00));
        ProductECommerce createdProduct = this.productECommerceService.create(product);
        assertNotNull(createdProduct);
        assertEquals("Laptop", createdProduct.getProductName());
        assertEquals(10, createdProduct.getNumberProduct());
        assertEquals(BigDecimal.valueOf(1200.00), createdProduct.getUnitPrice());
    }

}
