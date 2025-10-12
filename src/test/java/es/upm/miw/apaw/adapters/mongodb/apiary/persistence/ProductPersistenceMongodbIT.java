package es.upm.miw.apaw.adapters.mongodb.apiary.persistence;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.apiary.Product;
import es.upm.miw.apaw.domain.persistenceports.apiary.ProductPersistence;
import es.upm.miw.apaw.adapters.mongodb.apiary.daos.ApiarySeeder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ProductPersistenceMongodbIT {

    @Autowired
    private ProductPersistence productPersistence;

    @Autowired
    private ApiarySeeder apiarySeeder;

    @BeforeEach
    void setup() {
        apiarySeeder.deleteAll();
        apiarySeeder.seedDatabase();
    }

    @Test
    void testReadExistingProduct() {
        Product product = this.productPersistence.read("P002");
        assertNotNull(product);
        assertEquals("Miel de Tomillo", product.getProduct());
        assertEquals(new BigDecimal("7.00"), product.getPrice());
    }

    @Test
    void testReadNonExistingProduct() {
        assertThrows(NotFoundException.class, () -> this.productPersistence.read("NO_EXISTE"));
    }

    @Test
    void testUpdateProduct() {
        Product existing = this.productPersistence.read("P003");
        existing.setPrice(new BigDecimal("4.00"));
        existing.setProduct("Cera refinada de abeja");

        Product updated = this.productPersistence.update(existing);

        assertEquals("Cera refinada de abeja", updated.getProduct());
        assertEquals(new BigDecimal("4.00"), updated.getPrice());
    }

    @Test
    void testUpdatePrice() {
        Product updated = this.productPersistence.updatePrice("P004", new BigDecimal("10.50"));
        assertEquals(new BigDecimal("10.50"), updated.getPrice());

        Product readBack = this.productPersistence.read("P004");
        assertEquals(new BigDecimal("10.50"), readBack.getPrice());
    }

    @Test
    void testUpdatePriceNotFound() {
        assertThrows(NotFoundException.class,
                () -> this.productPersistence.updatePrice("NO_EXISTE", new BigDecimal("9.99")));
    }
}
