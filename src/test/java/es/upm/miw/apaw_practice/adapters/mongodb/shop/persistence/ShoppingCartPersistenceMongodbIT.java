package es.upm.miw.apaw_practice.adapters.mongodb.shop.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.ShopSeederService;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
import es.upm.miw.apaw_practice.domain.models.shop.ArticleItem;
import es.upm.miw.apaw_practice.domain.models.shop.ShoppingCart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ShoppingCartPersistenceMongodbIT {

    @Autowired
    private ShoppingCartPersistenceMongodb shoppingCartPersistenceMongodb;

    @Autowired
    private ShopSeederService shopSeederService;

    @Test
    void testReadById() {
        Optional<ShoppingCart> shoppingCart = this.shoppingCartPersistenceMongodb.readAll()
                .filter(cart -> "user1".equals(cart.getUser()))
                .findFirst();
        assertTrue(shoppingCart.isPresent());
        assertNotNull(shoppingCart.get().getId());
        assertNotNull(shoppingCart.get().getCreationDate());
    }

    @Test
    void testUpdate() {
        Optional<ShoppingCart> shoppingCart = this.shoppingCartPersistenceMongodb.readAll()
                .filter(cart -> "user1".equals(cart.getUser()))
                .findFirst();
        assertTrue(shoppingCart.isPresent());
        List<ArticleItem> articleItems = shoppingCart.get().getArticleItems();
        articleItems.clear();
        Article article = new Article("84003", "art 003", new BigDecimal("12.13"), "prov 3");
        articleItems.add(new ArticleItem(article, 3, BigDecimal.ZERO));
        this.shoppingCartPersistenceMongodb.update(shoppingCart.get());
        Optional<ShoppingCart> newShoppingCart = this.shoppingCartPersistenceMongodb.readAll()
                .filter(cart -> "user1".equals(cart.getUser()))
                .findFirst();
        assertTrue(newShoppingCart.isPresent());
        assertEquals(shoppingCart.get().getCreationDate(), newShoppingCart.get().getCreationDate());
        assertEquals(1, newShoppingCart.get().getArticleItems().size());
        assertEquals("84003", newShoppingCart.get().getArticleItems().get(0).getArticle().getBarcode());
        shopSeederService.deleteAll();
        shopSeederService.seedDatabase();
    }
}
