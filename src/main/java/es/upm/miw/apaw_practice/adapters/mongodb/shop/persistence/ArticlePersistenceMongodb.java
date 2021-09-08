package es.upm.miw.apaw_practice.adapters.mongodb.shop.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.shop.daos.ArticleRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.ArticleEntity;
import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
import es.upm.miw.apaw_practice.domain.models.shop.ArticleCreation;
import es.upm.miw.apaw_practice.domain.persistence_ports.shop.ArticlePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Repository("articlePersistence")
public class ArticlePersistenceMongodb implements ArticlePersistence {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticlePersistenceMongodb(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article readByBarcode(Long barcode) {
        return this.articleRepository
                .findByBarcode(barcode)
                .orElseThrow(() -> new NotFoundException("Article barcode: " + barcode))
                .toArticle();
    }

    public void assertBarcodeNotExist(Long barcode) {
        this.articleRepository
                .findByBarcode(barcode)
                .ifPresent(article -> {
                    throw new ConflictException("Barcode exist: " + barcode);
                });
    }

    @Override
    public Stream<Article> findByProviderAndPriceGreaterThan(String provider, BigDecimal price) {
        return this.articleRepository.findAll().stream()
                .filter(article -> provider.equals(article.getProvider()))
                .filter(article -> price.compareTo(article.getPrice()) < 0)
                .map(ArticleEntity::toArticle);
    }

    @Override
    public Stream<Article> readAll() {
        return this.articleRepository
                .findAll().stream()
                .map(ArticleEntity::toArticle);
    }

    @Override
    public Article create(ArticleCreation articleCreation) {
        this.assertBarcodeNotExist(articleCreation.getBarcode());
        return this.articleRepository
                .save(new ArticleEntity(articleCreation))
                .toArticle();
    }

    @Override
    public Article update(Article article) {
        ArticleEntity articleEntity = this.articleRepository
                .findById(article.getId())
                .orElseThrow(() -> new NotFoundException("Article id: " + article.getId()));
        articleEntity.fromArticle(article);
        return this.articleRepository
                .save(articleEntity)
                .toArticle();
    }

}
