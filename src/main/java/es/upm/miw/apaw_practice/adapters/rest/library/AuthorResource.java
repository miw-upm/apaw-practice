package es.upm.miw.apaw_practice.adapters.rest.library;

import es.upm.miw.apaw_practice.domain.models.library.Author;
import es.upm.miw.apaw_practice.domain.services.library.AuthorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AuthorResource.AUTHORS)
public class AuthorResource {
    static  final String AUTHORS = "/library/authors";
    static final String ID_ID = "/{id}";

    private final AuthorService authorService;

    public AuthorResource(AuthorService authorService) {
        this.authorService = authorService;
    }
    @PutMapping(ID_ID)
    public Author update(@PathVariable String id, @RequestBody Author author){
        return this.authorService.update(id, author);
    }
}
