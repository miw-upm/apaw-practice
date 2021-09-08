package es.upm.miw.apaw_practice.adapters.mongodb.shop;

import es.upm.miw.apaw_practice.adapters.mongodb.shop.daos.ArticleRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.daos.ShoppingCartRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.daos.TagRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.ArticleEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.ArticleItemEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.ShoppingCartEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.TagEntity;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class ShopSeederService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private TagRepository tagRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Shop Initial Load -----------");
        ArticleEntity[] articles = {
                new ArticleEntity(new Article("84001", "art 001", new BigDecimal("1.23"), "prov 1")),
                new ArticleEntity(new Article("84002", "art 002", new BigDecimal("0.27"), "prov 2")),
                new ArticleEntity(new Article("84003", "art 003", new BigDecimal("12.13"), "prov 3")),
                new ArticleEntity(new Article("84004", "art 004", new BigDecimal("4.00"), "prov 4")),
                new ArticleEntity(new Article("84005", "art 005", new BigDecimal("0.45"), "prov 5"))
        };
        this.articleRepository.saveAll(Arrays.asList(articles));
        TagEntity[] tags = {
                new TagEntity("tag1", "tag 1", List.of(articles[0], articles[1]), false),
                new TagEntity("tag2", "tag 2", List.of(articles[0], articles[3]), true),
                new TagEntity("tag3", "tag 3", List.of(articles[1]), false),
                new TagEntity("tag4", "tag 4", List.of(articles[4]), false)
        };
        this.tagRepository.saveAll(Arrays.asList(tags));
        ArticleItemEntity[] articleItems = {
                new ArticleItemEntity(articles[0], 1, BigDecimal.ZERO),
                new ArticleItemEntity(articles[1], 2, BigDecimal.TEN),
                new ArticleItemEntity(articles[1], 3, BigDecimal.ZERO),
                new ArticleItemEntity(articles[2], 4, BigDecimal.ONE)
        };
        ShoppingCartEntity[] carts = {
                new ShoppingCartEntity(Arrays.asList(articleItems[0], articleItems[1]), "user1", "address 1"),
                new ShoppingCartEntity(Arrays.asList(articleItems[2], articleItems[3]), "user2", "address 2")
        };
        this.shoppingCartRepository.saveAll(Arrays.asList(carts));
    }

    public void deleteAll() {
        this.shoppingCartRepository.deleteAll();
        this.tagRepository.deleteAll();
        this.articleRepository.deleteAll();
    }
}
