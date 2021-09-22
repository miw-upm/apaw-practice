package es.upm.miw.apaw_practice.domain.services.shop;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
import es.upm.miw.apaw_practice.domain.models.shop.ArticlePriceUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.shop.ArticlePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

@Service
public class ArticleService {

    private final ArticlePersistence articlePersistence;

    @Autowired
    public ArticleService(ArticlePersistence articlePersistence) {
        this.articlePersistence = articlePersistence;
    }

    public Article create(Article article) {
        article.setRegistrationDate(LocalDate.now());
        this.assertBarcodeNotExist(article.getBarcode());
        return this.articlePersistence.create(article);
    }

    public void assertBarcodeNotExist(String barcode) {
        if (this.articlePersistence.existBarcode(barcode)) {
            throw new ConflictException("Barcode exist: " + barcode);
        }
    }

    public void updatePrices(Stream<ArticlePriceUpdating> articlePriceUpdatingList) {
        articlePriceUpdatingList.map(articleNewPrice -> {
                    Article article = this.articlePersistence.read(articleNewPrice.getBarcode());
                    article.setPrice(articleNewPrice.getPrice());
                    return article;
                })
                .forEach(article -> this.articlePersistence.update(article.getBarcode(), article));
    }

    public Stream<Article> findByProviderAndPriceGreaterThan(String provider, BigDecimal price) {
        return this.articlePersistence.findByProviderAndPriceGreaterThan(provider, price);
    }
}
