package es.upm.miw.apaw.adapters.mongodb.shop.daos;

import es.upm.miw.apaw.adapters.mongodb.shop.entities.ArticleEntity;
import es.upm.miw.apaw.adapters.mongodb.shop.entities.TagEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
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
        assertThat(tag.getName()).isEqualTo("tag2");
        assertThat(tag.getDescription()).isEqualTo("tag 2");
        assertThat(tag.getArticleEntities())
                .extracting(ArticleEntity::getBarcode)
                .containsExactlyInAnyOrder("84001", "84004");
        assertThat(tag.getFavourite()).isTrue();

    }
}
