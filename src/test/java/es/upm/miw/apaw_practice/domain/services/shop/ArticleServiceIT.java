package es.upm.miw.apaw_practice.domain.services.shop;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.shop.ArticlePriceUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.shop.ArticlePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class ArticleServiceIT {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticlePersistence articlePersistence;

    @Test
    void testUpdatePrices() {
        List<ArticlePriceUpdating> articlePriceUpdatingList = List.of(
                new ArticlePriceUpdating(84002L, BigDecimal.ONE),
                new ArticlePriceUpdating(84003L, BigDecimal.TEN)
        );
        this.articleService.updatePrices(articlePriceUpdatingList);
        assertEquals(0, BigDecimal.ONE.compareTo(this.articlePersistence.readByBarcode(84002L).getPrice()));
        assertEquals(0, BigDecimal.TEN.compareTo(this.articlePersistence.readByBarcode(84003L).getPrice()));
        articlePriceUpdatingList = List.of(
                new ArticlePriceUpdating(84002L, new BigDecimal("0.27")),
                new ArticlePriceUpdating(84003L, new BigDecimal("12.13"))
        );
        this.articleService.updatePrices(articlePriceUpdatingList);
    }

}
