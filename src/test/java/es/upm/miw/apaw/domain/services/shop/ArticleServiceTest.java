package es.upm.miw.apaw.domain.services.shop;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.shop.Article;
import es.upm.miw.apaw.domain.persistenceports.shop.ArticlePersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @MockitoBean
    private ArticlePersistence articlePersistence;

    @Test
    void testCreate() {
        BDDMockito.given(this.articlePersistence.existBarcode(Mockito.anyString()))
                .willReturn(false);

        BDDMockito.given(this.articlePersistence.create(Mockito.any()))
                .willAnswer(invocation -> invocation.getArgument(0));

        Article article = Article.builder().barcode("8466001").summary("art test").price(BigDecimal.ONE).provider("prov 1").build();

        assertThat(this.articleService.create(article).getBarcode()).isEqualTo("8466001");
    }

    @Test
    void testCreateConflictException() {
        BDDMockito.given(this.articlePersistence.existBarcode(Mockito.anyString()))
                .willReturn(true);

        Article article = Article.builder().barcode("8466001").summary("art test").price(BigDecimal.ONE).provider("prov 1").build();

        assertThatThrownBy(() -> this.articleService.create(article))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("8466001");
    }

}
