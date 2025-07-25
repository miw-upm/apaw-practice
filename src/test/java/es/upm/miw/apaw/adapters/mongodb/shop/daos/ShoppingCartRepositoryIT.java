package es.upm.miw.apaw.adapters.mongodb.shop.daos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class ShoppingCartRepositoryIT {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.shoppingCartRepository.findAll().stream()
                .anyMatch(cart ->
                        UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000").equals(cart.getUser()) &&
                                "address 1" .equals(cart.getAddress()) &&
                                cart.getId() != null &&
                                cart.getCreationDate() != null &&
                                cart.getCreationDate().isBefore(LocalDateTime.now()) &&
                                2 == cart.getArticleItemEntities().size() &&
                                "84001" .equals(cart.getArticleItemEntities().getFirst().getArticleEntity().getBarcode()) &&
                                1 == cart.getArticleItemEntities().getFirst().getAmount() &&
                                0 == BigDecimal.ZERO.compareTo(cart.getArticleItemEntities().getFirst().getDiscount())
                ));
    }
}
