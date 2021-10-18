package es.upm.miw.apaw_practice.adapters.mongodb.library.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class CategoryRepositoryIT {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testFindByName() {
        assertTrue(this.categoryRepository.findByName("Horror").isPresent());
        CategoryEntity categoryEntity = this.categoryRepository.findByName("Drama").get();
        assertEquals("Drama", categoryEntity.getName());
        assertEquals("a conflict that takes place in the lives of character", categoryEntity.getDescription());
    }
}
