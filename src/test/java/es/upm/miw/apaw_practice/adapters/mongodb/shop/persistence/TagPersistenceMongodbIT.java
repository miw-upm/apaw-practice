package es.upm.miw.apaw_practice.adapters.mongodb.shop.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.shop.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class TagPersistenceMongodbIT {

    @Autowired
    private TagPersistenceMongodb tagPersistence;

    @Test
    void testReadById() {
        Tag tag = this.tagPersistence.readById("tag2");
        assertEquals("tag 2", tag.getDescription());
        assertTrue(tag.getFavourite());
        assertEquals(2, tag.getArticlesBarcode().size());
        assertTrue(tag.getArticlesBarcode().containsAll(List.of(84001L, 84004L)));
    }

}
