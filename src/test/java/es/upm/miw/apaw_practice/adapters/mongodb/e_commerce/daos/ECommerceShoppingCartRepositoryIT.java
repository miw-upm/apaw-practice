package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.ECommerceSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ShoppingCartEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ECommerceShoppingCartRepositoryIT {

    @Autowired
    private ECommerceShoppingCartRepository eCommerceShoppingCartRepository;

    @Autowired
    private ECommerceSeederService eCommerceSeederService;

    @BeforeEach
    void setUp() {
        eCommerceSeederService.deleteAll();
        eCommerceSeederService.seedDatabase();
    }

    @Test
    void testFindByShoppingNumNonExisting() {
        Optional<ShoppingCartEntity> shoppingCartEntity = eCommerceShoppingCartRepository.findByShoppingNum(9999);
        assertTrue(shoppingCartEntity.isEmpty(), "ShoppingCart should not exist for this shopping number");
    }

    @Test
    void testFindByShoppingNum() {
        Optional<ShoppingCartEntity> shoppingCartEntity = eCommerceShoppingCartRepository.findByShoppingNum(1);
        assertTrue(shoppingCartEntity.isPresent(), "ShoppingCart should exist for this shopping number");

        ShoppingCartEntity entity = shoppingCartEntity.get();
        assertNotNull(entity);

        assertEquals(1, entity.getShoppingNum(), "Shopping number should match");
        assertEquals(false, entity.getIsPaid(), "Paid status should match");
        assertEquals(1800.00, entity.getTotalPrice().doubleValue(), "Total price should match");
    }
}

