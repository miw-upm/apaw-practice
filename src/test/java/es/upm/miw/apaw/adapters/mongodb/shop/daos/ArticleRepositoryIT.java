package es.upm.miw.apaw.adapters.mongodb.shop.daos;

import es.upm.miw.apaw.adapters.mongodb.shop.entities.ArticleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
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
        assertThat(article.getSummary()).isEqualTo("art 003");
        assertThat(article.getPrice()).isEqualByComparingTo("12.13");
        assertThat(article.getProvider()).isEqualTo("prov 3");
    }

}
