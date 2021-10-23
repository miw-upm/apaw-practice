package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.ActiveIngredientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ActiveIngredientRepository extends MongoRepository<ActiveIngredientEntity, String> {

    Optional<ActiveIngredientEntity> findByCode(String code);
}
