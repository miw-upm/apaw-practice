package es.upm.miw.apaw_practice.adapters.mongodb.shop.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class ShoppingCartRepositoryIT {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.shoppingCartRepository.findAll().stream()
                .anyMatch(cart ->
                        "user1".equals(cart.getUser()) &&
                                "address 1".equals(cart.getAddress()) &&
                                cart.getId() != null &&
                                cart.getCreationDate() != null &&
                                cart.getCreationDate().isBefore(LocalDateTime.now()) &&
                                2 == cart.getArticleItemEntities().size() &&
                                "84001".equals(cart.getArticleItemEntities().get(0).getArticleEntity().getBarcode()) &&
                                1 == cart.getArticleItemEntities().get(0).getAmount() &&
                                0 == BigDecimal.ZERO.compareTo(cart.getArticleItemEntities().get(0).getDiscount())
                ));
    }
}
