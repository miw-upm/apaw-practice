package es.upm.miw.apaw_practice.adapters.mongodb.library.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.library.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class CategoryPersistenceMongodbIT {

    @Autowired
    private CategoryPersistenceMongodb categoryPersistenceMongodb;

    @Test
    void testFindByName() {
        Category category = this.categoryPersistenceMongodb.findByName("Drama");
        assertEquals("Drama", category.getName());
        assertEquals("a conflict that takes place in the lives of character", category.getDescription());
    }

    @Test
    void testUpdate() {
        Category category = new Category("Poetry", "Test Description");
        Category cat = this.categoryPersistenceMongodb.update(category);
        assertEquals(cat.getDescription(), category.getDescription());
        assertEquals(cat.getName(), category.getName());
    }

    @Test
    void testUpdateFail() {
        Category category = new Category("Horro", "causes discomfor and fear");
        assertThrows(NotFoundException.class, () -> this.categoryPersistenceMongodb.update(category));
    }
}
