package es.upm.miw.apaw.adapters.mongodb.shop;

import es.upm.miw.apaw.adapters.mongodb.shop.daos.ArticleRepository;
import es.upm.miw.apaw.adapters.mongodb.shop.daos.ShoppingCartRepository;
import es.upm.miw.apaw.adapters.mongodb.shop.daos.TagRepository;
import es.upm.miw.apaw.adapters.mongodb.shop.entities.ArticleEntity;
import es.upm.miw.apaw.adapters.mongodb.shop.entities.ArticleItemEntity;
import es.upm.miw.apaw.adapters.mongodb.shop.entities.ShoppingCartEntity;
import es.upm.miw.apaw.adapters.mongodb.shop.entities.TagEntity;
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
                ArticleEntity.builder().barcode("84001").summary("art 001").price(new BigDecimal("1.23")).provider("prov 1").build(),
                ArticleEntity.builder().barcode("84002").summary("art 002").price(new BigDecimal("0.27")).provider("prov 2").build(),
                ArticleEntity.builder().barcode("84003").summary("art 003").price(new BigDecimal("12.13")).provider("prov 3").build(),
                ArticleEntity.builder().barcode("84004").summary("art 004").price(new BigDecimal("4.00")).provider("prov 4").build(),
                ArticleEntity.builder().barcode("84005").summary("art 005").price(new BigDecimal("0.45")).provider("prov 5").build()
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
