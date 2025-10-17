package es.upm.miw.apaw.adapters.mongodb.apiary.daos;

import es.upm.miw.apaw.adapters.mongodb.apiary.entities.ProductEntity;
import es.upm.miw.apaw.adapters.mongodb.apiary.entities.SaleEntity;
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
    void testDeleteByBarcode() {
        int deletedCount = this.productRepository.deleteByBarcode("P005");
        assertEquals(1, deletedCount);
        assertTrue(this.productRepository.findByBarcode("P005").isEmpty());
    }

    @Test
    void testToProductCoversIfBranches() {
        ProductEntity entityNull = ProductEntity.builder()
                .barcode("test-null")
                .product("Miel Null")
                .price(BigDecimal.ONE)
                .saleEntities(null)
                .build();
        assertNull(entityNull.toProduct().getSales());

        SaleEntity sale = SaleEntity.builder().idSale(42).build();
        ProductEntity entityNotNull = ProductEntity.builder()
                .barcode("test-null")
                .product("Miel NotNull")
                .price(BigDecimal.TEN)
                .saleEntities(List.of(sale))
                .build();
        assertNotNull(entityNotNull.toProduct().getSales());
        assertEquals(1, entityNotNull.toProduct().getSales().size());
        assertEquals(42, entityNotNull.toProduct().getSales().get(0).getIdSale());
    }
}
