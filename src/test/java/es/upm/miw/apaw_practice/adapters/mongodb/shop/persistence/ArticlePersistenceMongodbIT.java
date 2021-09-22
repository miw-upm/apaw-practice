package es.upm.miw.apaw_practice.adapters.mongodb.shop.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
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
        Article article =
                new Article("6661001", "art per", new BigDecimal("3.00"), "prov per");
        this.articlePersistence.create(article);
        Article articleBD = this.articlePersistence.read("6661001");
        assertEquals("art per", articleBD.getSummary());
        assertEquals(0, new BigDecimal("3.00").compareTo(articleBD.getPrice()));
        assertEquals("prov per", articleBD.getProvider());
    }

    @Test
    void testCreateAndUpdate() {
        Article articleCreation =
                new Article("6661002", "art per", new BigDecimal("3.00"), "prov per");
        Article articleBD = this.articlePersistence.create(articleCreation);
        articleBD.setPrice(BigDecimal.TEN);
        this.articlePersistence.update("6661002", articleBD);
        articleBD = this.articlePersistence.read("6661002");
        assertEquals(0, BigDecimal.TEN.compareTo(articleBD.getPrice()));
    }

}
