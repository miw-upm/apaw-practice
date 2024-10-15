package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities.ProviderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProviderRepository extends MongoRepository<ProviderEntity, String> {
    Optional<ProviderEntity> findByName(String name);
}
