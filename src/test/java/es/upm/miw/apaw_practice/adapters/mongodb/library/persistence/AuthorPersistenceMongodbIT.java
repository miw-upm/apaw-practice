package es.upm.miw.apaw_practice.adapters.mongodb.library.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.library.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class AuthorPersistenceMongodbIT {

    @Autowired
    private AuthorPersistenceMongodb authorPersistenceMongodb;

    @Test
    void testUpdate() {
        Author author = Author.builder("Alda do Espírito Santo", "Sao Tome and Principe").writingStyle("Poetry").build();
        Author author1 = this.authorPersistenceMongodb.update(author);
        assertEquals(author1.getFullName(), author.getFullName());
        assertEquals(author1.getNationality(), author.getNationality());
    }
}
