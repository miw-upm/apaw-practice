package es.upm.miw.apaw.adapters.mongodb.shop.daos;

import es.upm.miw.apaw.adapters.mongodb.shop.entities.ArticleEntity;
import es.upm.miw.apaw.adapters.mongodb.shop.entities.ArticleItemEntity;
import es.upm.miw.apaw.adapters.mongodb.shop.entities.ShoppingCartEntity;
import es.upm.miw.apaw.adapters.mongodb.shop.entities.TagEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class ShopSeeder {

    private final ArticleRepository articleRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final TagRepository tagRepository;

    @Autowired
    public ShopSeeder(ArticleRepository articleRepository, TagRepository tagRepository, ShoppingCartRepository shoppingCartRepository) {
        this.articleRepository = articleRepository;
        this.tagRepository = tagRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void seedDatabase() {
        log.warn("------- Shop Initial Load -----------");
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
                new ShoppingCartEntity(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"), LocalDateTime.now(), Arrays.asList(articleItems[0], articleItems[1]), UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003")),
                new ShoppingCartEntity(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"), LocalDateTime.now(), Arrays.asList(articleItems[2], articleItems[3]), UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
        };
        this.shoppingCartRepository.saveAll(Arrays.asList(carts));
        log.warn("        ------- shop");
    }

    public void deleteAll() {
        this.shoppingCartRepository.deleteAll();
        this.tagRepository.deleteAll();
        this.articleRepository.deleteAll();
    }
}
