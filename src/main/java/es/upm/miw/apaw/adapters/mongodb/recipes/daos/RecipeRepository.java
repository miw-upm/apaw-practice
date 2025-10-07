package es.upm.miw.apaw.adapters.mongodb.recipes.daos;

import es.upm.miw.apaw.adapters.mongodb.recipes.entities.RecipeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface RecipeRepository extends MongoRepository<RecipeEntity, UUID> {
}