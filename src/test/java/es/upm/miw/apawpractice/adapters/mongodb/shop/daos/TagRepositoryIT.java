package es.upm.miw.apawpractice.adapters.mongodb.shop.daos;

import es.upm.miw.apawpractice.adapters.mongodb.shop.entities.ArticleEntity;
import es.upm.miw.apawpractice.adapters.mongodb.shop.entities.TagEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class TagRepositoryIT {

    @Autowired
    private TagRepository tagRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.tagRepository.findByName("tag2").isPresent());
        TagEntity tag = this.tagRepository.findByName("tag2").get();
        assertEquals("tag2", tag.getName());
        assertEquals("tag 2", tag.getDescription());
        assertTrue(tag.getArticleEntities().stream()
                .map(ArticleEntity::getBarcode)
                .toList()
                .containsAll(Arrays.asList("84001", "84004")));
        assertTrue(tag.getFavourite());

    }
}
