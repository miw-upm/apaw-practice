package es.upm.miw.apaw_practice.adapters.rest.e_commerce;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos.ECommerceShoppingCartRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ShoppingCartECommerceEntity;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestTestConfig
public class ShoppingCartECommerceResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ECommerceShoppingCartRepository shoppingCartRepository;

    private Integer existingShoppingCartShoppingNum;

    @BeforeEach
    void setUp() {
        ShoppingCartECommerceEntity shoppingCart = new ShoppingCartECommerceEntity(
                1, LocalDateTime.now(), false, new BigDecimal("500.0"), List.of()
        );
        shoppingCart = this.shoppingCartRepository.save(shoppingCart);
        existingShoppingCartShoppingNum = shoppingCart.getShoppingNum();
    }

    @Test
    void testDeleteShoppingCart() {
        Integer existingShoppingNum = 4;

        this.webTestClient
                .delete()
                .uri(ShoppingCartECommerceResource.SHOPPING_CARTS + "/" + existingShoppingNum)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testDeleteShoppingCartNotFound() {
        Integer nonExistentShoppingNum = 999;

        this.webTestClient
                .delete()
                .uri(ShoppingCartECommerceResource.SHOPPING_CARTS + "/" + nonExistentShoppingNum)
                .exchange()
                .expectStatus().isNotFound();
    }
}
