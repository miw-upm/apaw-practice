package es.upm.miw.apaw_practice.domain.services.library;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.library.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class AuthorServiceIT {

    @Autowired
    private AuthorService authorService;

    @Test
    void testUpdate(){
        Author author = new Author("Juan Gómez-Jurado","Portuguese","Thriller");
        Author authorUpdated = this.authorService.update(author);
        assertEquals("Portuguese",authorUpdated.getNationality());
    }
}
