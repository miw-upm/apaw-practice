package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos.RaidRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.RaidEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.ArticleItemEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.ShoppingCartEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.game_wow.Raid;
import es.upm.miw.apaw_practice.domain.models.shop.ShoppingCart;
import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.RaidPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("raidPersistence")
public class RaidPersistenceMongodb implements RaidPersistence {

    private RaidRepository raidRepository;

    @Autowired
    public RaidPersistenceMongodb(RaidRepository raidRepository) {
        this.raidRepository = raidRepository;
    }

    @Override
    public Raid readById(String id) {
        return this.raidRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("raid id:" + id))
                .toRaid();
    }

    @Override
    public Raid update(Raid raid) {
        RaidEntity raidEntity = this.raidRepository
                .findById(raid.getId())
                .orElseThrow(() -> new NotFoundException("raid id:" + raid.getId()));

        /*List<ArticleItemEntity> articleItemEntities = shoppingCart.getArticleItems().stream()
                .map(articleItem -> new ArticleItemEntity(
                        this.articleRepository
                                .findByBarcode(articleItem.getArticle().getBarcode())
                                .orElseThrow(() -> new NotFoundException("Article barcode: "
                                        + articleItem.getArticle().getBarcode())),
                        articleItem.getAmount(),
                        articleItem.getDiscount())

                ).collect(Collectors.toList());*/

        //raidEntity.setArticleItemEntities(articleItemEntities);
        return this.raidRepository.save(raidEntity).toRaid();
    }
}
