package es.upm.miw.apaw.adapters.mongodb.shop.persistence;

import es.upm.miw.apaw.domain.models.shop.Article;
import es.upm.miw.apaw.domain.models.shop.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class TagPersistenceMongodbIT {

    @Autowired
    private TagPersistenceMongodb tagPersistence;

    @Test
    void testReadByName() {
        Tag tag = this.tagPersistence.readByName("tag2");
        assertEquals("tag 2", tag.getDescription());
        assertTrue(tag.getFavourite());
        assertEquals(2, tag.getArticles().size());
        assertTrue(tag.getArticles().stream()
                .map(Article::getBarcode)
                .toList()
                .containsAll(List.of("84001", "84004")));
    }

}
