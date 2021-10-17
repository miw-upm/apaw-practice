package es.upm.miw.apaw_practice.domain.services.library;

import es.upm.miw.apaw_practice.domain.models.library.Author;
import es.upm.miw.apaw_practice.domain.persistence_ports.library.AuthorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorPersistence authorPersistence;

    @Autowired
    public AuthorService(AuthorPersistence authorPersistence) {
        this.authorPersistence = authorPersistence;
    }


    public Author update(String id, Author authorParam) {
        return this.authorPersistence.update(id, authorParam);
    }
}
