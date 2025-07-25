package es.upm.miw.apaw.adapters.mongodb.shop.persistence;

import es.upm.miw.apaw.adapters.mongodb.shop.ShopSeederService;
import es.upm.miw.apaw.domain.models.shop.Article;
import es.upm.miw.apaw.domain.models.shop.ArticleItem;
import es.upm.miw.apaw.domain.models.shop.ShoppingCart;
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
class ShoppingCartPersistenceMongodbIT {

    @Autowired
    private ShoppingCartPersistenceMongodb shoppingCartPersistenceMongodb;

    @Autowired
    private ShopSeederService shopSeederService;

    @Test
    void testReadById() {
        Optional<ShoppingCart> shoppingCart = this.shoppingCartPersistenceMongodb.readAll()
                .filter(cart -> "user1" .equals(cart.getUser()))
                .findFirst();
        assertTrue(shoppingCart.isPresent());
        assertNotNull(shoppingCart.get().getId());
        assertNotNull(shoppingCart.get().getCreationDate());
    }

    @Test
    void testUpdate() {
        Optional<ShoppingCart> shoppingCart = this.shoppingCartPersistenceMongodb.readAll()
                .filter(cart -> "user1" .equals(cart.getUser()))
                .findFirst();
        assertTrue(shoppingCart.isPresent());
        Article article = Article.builder().barcode("84003").summary("art 003").price(new BigDecimal("12.13"))
                .provider("prov 3").build();
        shoppingCart.get().setArticleItems(List.of(new ArticleItem(article, 3, BigDecimal.ZERO)));
        this.shoppingCartPersistenceMongodb.update(shoppingCart.get());
        Optional<ShoppingCart> newShoppingCart = this.shoppingCartPersistenceMongodb.readAll()
                .filter(cart -> "user1" .equals(cart.getUser()))
                .findFirst();
        assertTrue(newShoppingCart.isPresent());
        assertEquals(shoppingCart.get().getCreationDate(), newShoppingCart.get().getCreationDate());
        assertEquals(1, newShoppingCart.get().getArticleItems().size());
        assertEquals("84003", newShoppingCart.get().getArticleItems().getFirst().getArticle().getBarcode());
        shopSeederService.deleteAll();
        shopSeederService.seedDatabase();
    }
}
