package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistance;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.PlatformRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.VideoGameCompanyRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGameCompanyEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGameCompany;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.VideoGameCompanyPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository("videoGameCompanyPersistence")
public class VideoGameCompanyPersistenceMongodb implements VideoGameCompanyPersistence {

    private final VideoGameCompanyRepository videoGameCompanyRepository;

    private final List<PlatformRepository> platformRepositories;

    @Autowired
    public VideoGameCompanyPersistenceMongodb(VideoGameCompanyRepository videoGameCompanyRepository, List<PlatformRepository> platformRepositories) {
        this.videoGameCompanyRepository = videoGameCompanyRepository;
        this.platformRepositories = platformRepositories;
    }

    @Override
    public Stream<VideoGameCompany> readAll() {
        return this.videoGameCompanyRepository.findAll().stream()
                .map(VideoGameCompanyEntity::toVideoGameCompany);
    }

    @Override
    public VideoGameCompany readByName(String name) {
        return this.videoGameCompanyRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Company name:" + name))
                .toVideoGameCompany();
    }

    @Override
    public VideoGameCompany update(VideoGameCompany videoGameCompany) {
        return null;
    }

//    @Override
//    public VideoGameCompany update(VideoGameCompany videoGameCompany) {
//        VideoGameCompanyEntity videoGameCompanyEntity = this.videoGameCompanyRepository
//                .(videoGameCompany.getName())
//                .orElseThrow(() -> new NotFoundException("Company name:" + videoGameCompany.getName()));
//        List<PlatformEntity> platformEntities = videoGameCompany.getPlatforms().stream()
//                .map(platform -> new PlatformEntity(
//                        this.platformRepository
//                                .find
//                ))
//
//
//
//        videoGameCompanyEntity.setPlatformEntities(platformEntities);
//        return this.videoGameCompanyRepository.save(videoGameCompanyEntity).toVideoGameCompany();
//    }


//    @Override
//    public ShoppingCart update(ShoppingCart shoppingCart) {
//        ShoppingCartEntity shoppingCartEntity = this.shoppingCartRepository
//                .findById(shoppingCart.getId())
//                .orElseThrow(() -> new NotFoundException("ShoppingCart id:" + shoppingCart.getId()));
//
//        List<ArticleItemEntity> articleItemEntities = shoppingCart.getArticleItems().stream()
//                .map(articleItem -> new ArticleItemEntity(
//                        this.articleRepository
//                                .findByBarcode(articleItem.getArticle().getBarcode())
//                                .orElseThrow(() -> new NotFoundException("Article barcode: "
//                                        + articleItem.getArticle().getBarcode())),
//                        articleItem.getAmount(),
//                        articleItem.getDiscount())
//
//                ).collect(Collectors.toList());
//        shoppingCartEntity.setArticleItemEntities(articleItemEntities);
//        return this.shoppingCartRepository.save(shoppingCartEntity).toShoppingCart();
//    }

}