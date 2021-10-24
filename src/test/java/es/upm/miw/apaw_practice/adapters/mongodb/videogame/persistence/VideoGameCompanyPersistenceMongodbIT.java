package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistance.VideoGameCompanyPersistenceMongodb;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGameCompany;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@TestConfig
public class VideoGameCompanyPersistenceMongodbIT {

    @Autowired
    public VideoGameCompanyPersistenceMongodb videoGameCompanyPersistenceMongodb;


    @Test
    void testReadAll() {
        Optional<VideoGameCompany> videoGameCompany = this.videoGameCompanyPersistenceMongodb.readAll()
                .filter(company -> "nintendo".equals(company.getName()))
                .findFirst();
        assertTrue(videoGameCompany.isPresent());
        assertNotNull(videoGameCompany.get().getStockMarket());
        assertNotNull(videoGameCompany.get().getPlatforms());
    }

    @Test
    void readByName() {
        Optional<VideoGameCompany> videoGameCompany = Optional.of(this.videoGameCompanyPersistenceMongodb.readByName("nintendo"));
        assertNotNull(videoGameCompany.get().getName());
        assertNotNull(videoGameCompany.get().getPlatforms());
    }

    @Test
    void update() {

    }
//    @Test
//    void testUpdate() {
//        Optional<ShoppingCart> shoppingCart = this.shoppingCartPersistenceMongodb.readAll()
//                .filter(cart -> "user1".equals(cart.getUser()))
//                .findFirst();
//        assertTrue(shoppingCart.isPresent());
//        List<ArticleItem> articleItems = shoppingCart.get().getArticleItems();
//        articleItems.clear();
//        Article article = new Article("84003", "art 003", new BigDecimal("12.13"), "prov 3");
//        articleItems.add(new ArticleItem(article, 3, BigDecimal.ZERO));
//        this.shoppingCartPersistenceMongodb.update(shoppingCart.get());
//        Optional<ShoppingCart> newShoppingCart = this.shoppingCartPersistenceMongodb.readAll()
//                .filter(cart -> "user1".equals(cart.getUser()))
//                .findFirst();
//        assertTrue(newShoppingCart.isPresent());
//        assertEquals(shoppingCart.get().getCreationDate(), newShoppingCart.get().getCreationDate());
//        assertEquals(1, newShoppingCart.get().getArticleItems().size());
//        assertEquals("84003", newShoppingCart.get().getArticleItems().get(0).getArticle().getBarcode());
//        shopSeederService.deleteAll();
//        shopSeederService.seedDatabase();
//    }
}
