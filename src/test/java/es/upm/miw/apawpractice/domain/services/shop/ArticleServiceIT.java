package es.upm.miw.apawpractice.domain.services.shop;

import es.upm.miw.apawpractice.domain.models.shop.ArticlePriceUpdating;
import es.upm.miw.apawpractice.domain.persistenceports.shop.ArticlePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class ArticleServiceIT {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticlePersistence articlePersistence;

    @Test
    void testUpdatePrices() {
        List<ArticlePriceUpdating> articlePriceUpdatingList = List.of(
                new ArticlePriceUpdating("84002", BigDecimal.ONE),
                new ArticlePriceUpdating("84003", BigDecimal.TEN)
        );
        this.articleService.updatePrices(articlePriceUpdatingList.stream());
        assertEquals(0, BigDecimal.ONE.compareTo(this.articlePersistence.read("84002").getPrice()));
        assertEquals(0, BigDecimal.TEN.compareTo(this.articlePersistence.read("84003").getPrice()));
        articlePriceUpdatingList = List.of(
                new ArticlePriceUpdating("84002", new BigDecimal("0.27")),
                new ArticlePriceUpdating("84003", new BigDecimal("12.13"))
        );
        this.articleService.updatePrices(articlePriceUpdatingList.stream());
    }

}
