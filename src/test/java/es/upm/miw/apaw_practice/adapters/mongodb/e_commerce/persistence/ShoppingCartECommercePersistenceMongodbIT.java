package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.persistence;
import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos.ECommerceShoppingCartRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ShoppingCartECommerceEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ShoppingCartECommercePersistenceMongodbIT {

    @Autowired
    private ECommerceShoppingCartRepository eCommerceShoppingCartRepository;

    @Test
    void testCreateAndDeleteShoppingCart() {
        ShoppingCartECommerceEntity shoppingCart = new ShoppingCartECommerceEntity(
                999,
                LocalDateTime.now(),
                false,
                BigDecimal.valueOf(199.99),
                Collections.emptyList()
        );

        ShoppingCartECommerceEntity savedCart = this.eCommerceShoppingCartRepository.save(shoppingCart);
        assertNotNull(savedCart);
        assertTrue(this.eCommerceShoppingCartRepository.findByShoppingNum(savedCart.getShoppingNum()).isPresent());

        this.eCommerceShoppingCartRepository.deleteByShoppingNum(999);
        assertFalse(this.eCommerceShoppingCartRepository.findByShoppingNum(savedCart.getShoppingNum()).isPresent());
    }

    @Test
    void testDeleteShoppingCartNotFound() {
        assertFalse(this.eCommerceShoppingCartRepository.findByShoppingNum(99999).isPresent());
    }
}