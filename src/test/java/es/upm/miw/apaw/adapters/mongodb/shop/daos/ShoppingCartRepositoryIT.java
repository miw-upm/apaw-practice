package es.upm.miw.apaw.adapters.mongodb.shop.daos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ShoppingCartRepositoryIT {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Test
    void testCreateAndRead() {
        assertThat(this.shoppingCartRepository.findAll())
                .anySatisfy(cart -> {
                    assertThat(cart.getUserId())
                            .isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"));
                    assertThat(cart.getId()).isNotNull();
                    assertThat(cart.getCreationDate())
                            .isNotNull()
                            .isBefore(LocalDateTime.now());
                    assertThat(cart.getArticleItemEntities())
                            .hasSize(2);
                    assertThat(cart.getArticleItemEntities().getFirst().getArticleEntity().getBarcode())
                            .isEqualTo("84001");
                    assertThat(cart.getArticleItemEntities().getFirst().getAmount())
                            .isEqualTo(1);
                    assertThat(cart.getArticleItemEntities().getFirst().getDiscount())
                            .isZero();
                });

    }
}
