package es.upm.miw.apaw_practice.domain.persistence_ports.shop;

import es.upm.miw.apaw_practice.domain.models.shop.Article;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Repository
public interface ArticlePersistence {

    Stream<Article> readAll();

    Article create(Article article);

    Article update(String barcode, Article article);

    Article read(String barcode);

    boolean existBarcode(String barcode);

    Stream<Article> findByProviderAndPriceGreaterThan(String provider, BigDecimal price);
}
