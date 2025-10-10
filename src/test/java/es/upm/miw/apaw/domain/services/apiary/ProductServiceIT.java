package es.upm.miw.apaw.domain.services.apiary;

import es.upm.miw.apaw.adapters.mongodb.apiary.daos.ApiarySeeder;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.apiary.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceIT {

    @Autowired
    private ProductService productService;

    @Autowired
    private ApiarySeeder apiarySeeder;

    @BeforeEach
    void setup() {
        apiarySeeder.deleteAll();
        apiarySeeder.seedDatabase();
    }

    @Test
    void testUpdateProduct() {
        Product updatedProduct = Product.builder()
                .barcode("P002")
                .product("Miel de Tomillo Ecológica")
                .price(new BigDecimal("7.50"))
                .sales(List.of()) // no relevante para la prueba
                .build();

        Product result = this.productService.update(updatedProduct);

        assertEquals("P002", result.getBarcode());
        assertEquals("Miel de Tomillo Ecológica", result.getProduct());
        assertEquals(new BigDecimal("7.50"), result.getPrice());
    }

    @Test
    void testUpdatePrice() {
        Product updated = this.productService.updatePrice("P001", new BigDecimal("8.75"));
        assertEquals("P001", updated.getBarcode());
        assertEquals(new BigDecimal("8.75"), updated.getPrice());
    }

    @Test
    void testUpdatePriceNotFound() {
        assertThrows(NotFoundException.class,
                () -> this.productService.updatePrice("NO_EXISTE", new BigDecimal("5.00")));
    }
}
