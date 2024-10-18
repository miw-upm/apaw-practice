package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.ECommerceSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ProductECommerceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class    ProductCommerceRepositoryIT {

    @Autowired
    private ECommerceProductRepository productRepository;

    @Autowired
    private ECommerceSeederService eCommerceSeederService;

    @BeforeEach
    void setUp() {
        eCommerceSeederService.deleteAll();
        eCommerceSeederService.seedDatabase();
    }

    @Test
    void testFindByProductNameNonExisting() {
        Optional<ProductECommerceEntity> productEntity = productRepository.findByProductName("NonExistingProduct");
        assertTrue(productEntity.isEmpty(), "Product should not exist for this product name");
    }

    @Test
    void testFindByProductName() {
        Optional<ProductECommerceEntity> productEntity = productRepository.findByProductName("Laptop");
        assertTrue(productEntity.isPresent(), "Product should exist for this product name");

        ProductECommerceEntity entity = productEntity.get();
        assertNotNull(entity);

        assertEquals("Laptop", entity.getProductName(), "Product name should match");
        assertEquals(10, entity.getNumberProduct(), "Product Num should match");
        assertEquals(1200.00, entity.getUnitPrice().doubleValue(), "Product price should match");
    }
}

