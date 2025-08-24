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
                ArticleEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .barcode("84001").summary("art 001").price(new BigDecimal("1.23")).provider("prov 1").build(),
                ArticleEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .barcode("84002").summary("art 002").price(new BigDecimal("0.27")).provider("prov 2").build(),
                ArticleEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .barcode("84003").summary("art 003").price(new BigDecimal("12.13")).provider("prov 3").build(),
                ArticleEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .barcode("84004").summary("art 004").price(new BigDecimal("4.00")).provider("prov 4").build(),
                ArticleEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"))
                        .barcode("84005").summary("art 005").price(new BigDecimal("0.45")).provider("prov 5").build()
        };
        this.articleRepository.saveAll(Arrays.asList(articles));
        TagEntity[] tags = {
                TagEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0010")).name("tag1")
                        .description("tag 1").articleEntities(List.of(articles[0], articles[1])).favourite(false).build(),
                TagEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0011")).name("tag2")
                        .description("tag 2").articleEntities(List.of(articles[0], articles[3])).favourite(true).build(),
                TagEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0012")).name("tag3")
                        .description("tag 3").articleEntities(List.of(articles[1])).favourite(false).build(),
                TagEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0013")).name("tag4")
                        .description("tag 4").articleEntities(List.of(articles[4])).favourite(false).build()
        };
        this.tagRepository.saveAll(Arrays.asList(tags));
        ArticleItemEntity[] articleItems = {
                ArticleItemEntity.builder().articleEntity(articles[0]).amount(1).discount(BigDecimal.ZERO).build(),
                ArticleItemEntity.builder().articleEntity(articles[1]).amount(2).discount(BigDecimal.TEN).build(),
                ArticleItemEntity.builder().articleEntity(articles[1]).amount(3).discount(BigDecimal.ZERO).build(),
                ArticleItemEntity.builder().articleEntity(articles[2]).amount(4).discount(BigDecimal.ONE).build()
        };
        ShoppingCartEntity[] carts = {
                ShoppingCartEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0020"))
                        .creationDate(LocalDateTime.now()).articleItemEntities(Arrays.asList(articleItems[0], articleItems[1]))
                        .userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003")).build(),
                ShoppingCartEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0021"))
                        .creationDate(LocalDateTime.now()).articleItemEntities(Arrays.asList(articleItems[2], articleItems[3]))
                        .userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003")).build()
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
