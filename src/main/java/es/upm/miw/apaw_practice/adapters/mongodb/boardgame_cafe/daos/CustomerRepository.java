package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {
    Optional<CustomerEntity> findByEmail(String email);
}
