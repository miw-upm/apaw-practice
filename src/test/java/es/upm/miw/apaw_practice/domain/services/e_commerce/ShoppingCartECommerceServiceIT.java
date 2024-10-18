package es.upm.miw.apaw_practice.domain.services.e_commerce;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.ECommerceSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos.ECommerceShoppingCartRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ShoppingCartECommerceEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class ShoppingCartECommerceServiceIT {

    @Autowired
    private ShoppingCartECommerceService shoppingCartECommerceService;

    @Autowired
    private ECommerceShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ECommerceSeederService seederService;

    @BeforeEach
    void seedDatabase() {
        seederService.deleteAll();
        seederService.seedDatabase();
    }

    @Test
    void testDeleteShoppingCart() {
        Integer shoppingNum = 1;

        Optional<ShoppingCartECommerceEntity> cartBeforeDeletion = this.shoppingCartRepository.findByShoppingNum(shoppingNum);
        Assertions.assertTrue(cartBeforeDeletion.isPresent());

        this.shoppingCartECommerceService.delete(shoppingNum);

        Optional<ShoppingCartECommerceEntity> cartAfterDeletion = this.shoppingCartRepository.findByShoppingNum(shoppingNum);
        Assertions.assertFalse(cartAfterDeletion.isPresent());
    }
}
