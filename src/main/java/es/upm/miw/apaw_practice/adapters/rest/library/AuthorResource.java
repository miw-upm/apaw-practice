package es.upm.miw.apaw_practice.adapters.rest.library;

import es.upm.miw.apaw_practice.domain.models.library.Author;
import es.upm.miw.apaw_practice.domain.services.library.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AuthorResource.AUTHORS)
public class AuthorResource {
    static final String AUTHORS = "/library/authors";

    private final AuthorService authorService;

    @Autowired
    public AuthorResource(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PutMapping
    public Author update(@RequestBody Author author) {
        return this.authorService.update(author);
    }
}
