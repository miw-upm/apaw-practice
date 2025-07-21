package es.upm.miw.apawpractice.adapters.mongodb.shop.persistence;

import es.upm.miw.apawpractice.domain.exceptions.NotFoundException;
import es.upm.miw.apawpractice.domain.models.shop.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ArticlePersistenceMongodbIT {

    @Autowired
    private ArticlePersistenceMongodb articlePersistence;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.articlePersistence.read("0"));
    }

    @Test
    void testBarcodeNotExist() {
        assertFalse(this.articlePersistence.existBarcode("0"));
    }

    @Test
    void testBarcodeExist() {
        assertTrue(this.articlePersistence.existBarcode("84003"));
    }

    @Test
    void testCreateAndRead() {
        Article article = Article.builder().barcode("6661001").summary("art per").price(new BigDecimal("3.00"))
                .provider("prov per").build();
        this.articlePersistence.create(article);
        Article articleBD = this.articlePersistence.read("6661001");
        assertEquals("art per", articleBD.getSummary());
        assertEquals(0, new BigDecimal("3.00").compareTo(articleBD.getPrice()));
        assertEquals("prov per", articleBD.getProvider());
    }

    @Test
    void testCreateAndUpdate() {
        Article articleCreation = Article.builder().barcode("6661002").summary("art per")
                .price(new BigDecimal("3.00")).provider("prov per").build();
        Article articleBD = this.articlePersistence.create(articleCreation);
        articleBD.setPrice(BigDecimal.TEN);
        this.articlePersistence.update("6661002", articleBD);
        articleBD = this.articlePersistence.read("6661002");
        assertEquals(0, BigDecimal.TEN.compareTo(articleBD.getPrice()));
    }

}
