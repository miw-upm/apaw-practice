package es.upm.miw.apaw_practice.domain.services.library;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.library.Category;
import es.upm.miw.apaw_practice.domain.models.library.CategoryDescriptionUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.library.CategoryPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class CategoryServiceIT {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryPersistence categoryPersistence;

    @Test
    void testUpdateDescriptions(){
       List<CategoryDescriptionUpdating> categoryDescriptionUpdating = List.of(
                new CategoryDescriptionUpdating("Poetry", "titanic"),
                new CategoryDescriptionUpdating("Romance", "xcv")
        );
        this.categoryService.updateDescriptions(categoryDescriptionUpdating.stream());
        Category cat1 = this.categoryPersistence.findByName("Poetry");
        Category cat2 = this.categoryPersistence.findByName("Romance");
        assertEquals(categoryDescriptionUpdating.get(0).getName(),cat1.getName());
        assertEquals(categoryDescriptionUpdating.get(1).getName(), cat2.getName());
        categoryDescriptionUpdating = List.of(
                new CategoryDescriptionUpdating("Poetry", "authors choose a particular rhythm and style to evoke and portray various emotions and ideas"),
                new CategoryDescriptionUpdating("Romance", "makes your heart all warm and fuzzy focuses on the love story of the main protagonists")
        );
        this.categoryService.updateDescriptions(categoryDescriptionUpdating.stream());
    }
}
