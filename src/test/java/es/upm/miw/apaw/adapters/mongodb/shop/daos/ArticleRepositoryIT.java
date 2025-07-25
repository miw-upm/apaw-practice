package es.upm.miw.apaw.adapters.mongodb.shop.daos;

import es.upm.miw.apaw.adapters.mongodb.shop.entities.ArticleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class ArticleRepositoryIT {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void testFindByBarcode() {
        assertTrue(this.articleRepository.findByBarcode("84003").isPresent());
        ArticleEntity article = this.articleRepository.findByBarcode("84003").get();
        assertEquals("art 003", article.getSummary());
        assertEquals(0, new BigDecimal("12.13").compareTo(article.getPrice()));
        assertEquals("prov 3", article.getProvider());
    }

}
