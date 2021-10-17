package es.upm.miw.apaw_practice.adapters.mongodb.library.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.library.daos.AuthorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.AuthorEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.library.Author;
import es.upm.miw.apaw_practice.domain.persistence_ports.library.AuthorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("authorPersistence")
public class AuthorPersistenceMongodb implements AuthorPersistence {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorPersistenceMongodb(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author update(Author author) {
        AuthorEntity authorEntity = this.authorRepository
                .findByFullName(author.getFullName())
                .orElseThrow(() -> new NotFoundException("Author fullName: " + author.getFullName()));
        authorEntity.fromAuthor(author);
        return this.authorRepository.save(authorEntity).toAuthor();
    }
}
