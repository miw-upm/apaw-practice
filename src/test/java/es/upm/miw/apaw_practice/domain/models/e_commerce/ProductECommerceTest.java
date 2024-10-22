package es.upm.miw.apaw_practice.domain.models.e_commerce;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import es.upm.miw.apaw_practice.domain.models.e_commerce_model.ProductECommerce;
import org.junit.jupiter.api.Test;

public class ProductECommerceTest {

    @Test
    public void testBuilder() {
        String expectedProductName = "Smartphone";
        Integer expectedNumberProduct = 50;
        BigDecimal expectedUnitPrice = BigDecimal.valueOf(399.99);

        ProductECommerce expectedProduct = new ProductECommerce(expectedProductName, expectedNumberProduct, expectedUnitPrice);

        ProductECommerce actualProduct = ProductECommerce.builder()
                .productName(expectedProductName)
                .numberProduct(expectedNumberProduct)
                .unitPrice(expectedUnitPrice)
                .build();

        assertEquals(expectedProduct, actualProduct);
        assertNotNull(actualProduct);
        assertEquals(expectedProductName, actualProduct.getProductName());
        assertEquals(expectedNumberProduct, actualProduct.getNumberProduct());
        assertEquals(expectedUnitPrice, actualProduct.getUnitPrice());
    }
}

