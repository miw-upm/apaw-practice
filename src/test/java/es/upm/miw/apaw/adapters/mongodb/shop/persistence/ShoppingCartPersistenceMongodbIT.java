package es.upm.miw.apaw.adapters.mongodb.shop.persistence;

import es.upm.miw.apaw.adapters.mongodb.shop.daos.ShopSeeder;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ShoppingCartPersistenceMongodbIT {

    @Autowired
    private ShoppingCartPersistenceMongodb shoppingCartPersistenceMongodb;

    @Autowired
    private ShopSeeder shopSeeder;

    @Test
    void testReadById() {
        Optional<ShoppingCart> shoppingCart = this.shoppingCartPersistenceMongodb.readAll()
                .filter(cart -> UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003").equals(cart.getUser().getId()))
                .findFirst();
        assertThat(shoppingCart).isPresent();
        assertThat(shoppingCart.get().getId()).isNotNull();
        assertThat(shoppingCart.get().getCreationDate()).isNotNull();
    }

    @Test
    void testUpdate() {
        Optional<ShoppingCart> shoppingCart = this.shoppingCartPersistenceMongodb.readAll()
                .filter(cart -> UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003").equals(cart.getUser().getId()))
                .findFirst();
        assertThat(shoppingCart).isPresent();
        Article article = Article.builder().barcode("84003").summary("art 003").price(new BigDecimal("12.13"))
                .provider("prov 3").build();
        shoppingCart.get().setArticleItems(List.of(new ArticleItem(article, 3, BigDecimal.ZERO)));
        this.shoppingCartPersistenceMongodb.update(shoppingCart.get());
        Optional<ShoppingCart> newShoppingCart = this.shoppingCartPersistenceMongodb.readAll()
                .filter(cart -> UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003").equals(cart.getUser().getId()))
                .findFirst();
        assertThat(newShoppingCart).isPresent();
        assertThat(shoppingCart.get().getCreationDate()).isEqualTo(newShoppingCart.get().getCreationDate());
        assertThat(newShoppingCart.get().getArticleItems()).hasSize(1);
        assertThat(newShoppingCart.get().getArticleItems().getFirst().getArticle().getBarcode()).isEqualTo("84003");
        shopSeeder.deleteAll();
        shopSeeder.seedDatabase();
    }
}
