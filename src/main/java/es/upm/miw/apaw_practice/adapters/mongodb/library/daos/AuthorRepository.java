package es.upm.miw.apaw_practice.adapters.mongodb.library.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.AuthorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<AuthorEntity, String> {
    Optional<AuthorEntity> findByFullName(String fullName);
}
