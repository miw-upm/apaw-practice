package es.upm.miw.apaw.adapters.mongodb.apiary.daos;

import es.upm.miw.apaw.adapters.mongodb.apiary.entities.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ProductRepositoryIT {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ApiarySeeder apiarySeeder;

    @BeforeEach
    void seedDatabase() {
        apiarySeeder.deleteAll();
        apiarySeeder.seedDatabase();
    }

    @Test
    void testFindByBarcode() {
        Optional<ProductEntity> product = this.productRepository.findByBarcode("P001");
        assertTrue(product.isPresent());
        assertEquals("Miel de Romero", product.get().getProduct());
        assertEquals(new BigDecimal("8.00"), product.get().getPrice());
    }

    @Test
    void testFindByPriceGreaterThan() {
        List<ProductEntity> products = this.productRepository.findByPriceGreaterThan(new BigDecimal("7.50"));
        assertFalse(products.isEmpty());
        assertTrue(products.stream().anyMatch(p -> p.getBarcode().equals("P001"))); // Miel de Romero
        assertTrue(products.stream().anyMatch(p -> p.getBarcode().equals("P004"))); // Miel de Azahar
    }

    @Test
    void testFindByProductAndPriceGreaterThan() {
        List<ProductEntity> products = this.productRepository.findByProductAndPriceGreaterThan("Miel de Azahar", new BigDecimal("8.00"));
        assertEquals(1, products.size());
        assertEquals("P004", products.get(0).getBarcode());
    }

    @Test
    void testDeleteByBarcode() {
        int deletedCount = this.productRepository.deleteByBarcode("P005");
        assertEquals(1, deletedCount);
        assertTrue(this.productRepository.findByBarcode("P005").isEmpty());
    }
}
