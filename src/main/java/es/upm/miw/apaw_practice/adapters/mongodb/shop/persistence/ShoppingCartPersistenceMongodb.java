package es.upm.miw.apaw_practice.adapters.mongodb.shop.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.shop.daos.ArticleRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.daos.ShoppingCartRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.ArticleItemEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.ShoppingCartEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.shop.ShoppingCart;
import es.upm.miw.apaw_practice.domain.persistence_ports.shop.ShoppingCartPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("shoppingCartPersistence")
public class ShoppingCartPersistenceMongodb implements ShoppingCartPersistence {

    private final ShoppingCartRepository shoppingCartRepository;

    private final ArticleRepository articleRepository;

    @Autowired
    public ShoppingCartPersistenceMongodb(ShoppingCartRepository shoppingCartRepository, ArticleRepository articleRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.articleRepository = articleRepository;
    }


    @Override
    public Stream<ShoppingCart> readAll() {
        return this.shoppingCartRepository.findAll().stream()
                .map(ShoppingCartEntity::toShoppingCart);
    }

    @Override
    public ShoppingCart readById(String id) {
        return this.shoppingCartRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("ShoppingCart id:" + id))
                .toShoppingCart();
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        ShoppingCartEntity shoppingCartEntity = this.shoppingCartRepository
                .findById(shoppingCart.getId())
                .orElseThrow(() -> new NotFoundException("ShoppingCart id:" + shoppingCart.getId()));
        List<ArticleItemEntity> articleItemEntities = shoppingCart.getArticleItems().stream()
                .map(articleItem -> new ArticleItemEntity(
                        this.articleRepository
                                .findByBarcode(articleItem.getArticle().getBarcode())
                                .orElseThrow(() -> new NotFoundException("Article barcode: "
                                        + articleItem.getArticle().getBarcode())),
                        articleItem.getAmount(),
                        articleItem.getDiscount())

                ).collect(Collectors.toList());
        shoppingCartEntity.setArticleItemEntities(articleItemEntities);
        return this.shoppingCartRepository.save(shoppingCartEntity).toShoppingCart();
    }

}
