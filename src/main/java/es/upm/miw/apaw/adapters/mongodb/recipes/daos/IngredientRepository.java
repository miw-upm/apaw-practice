package es.upm.miw.apaw.adapters.mongodb.recipes.daos;

import es.upm.miw.apaw.adapters.mongodb.recipes.entities.IngredientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface IngredientRepository extends MongoRepository<IngredientEntity, UUID> {
}