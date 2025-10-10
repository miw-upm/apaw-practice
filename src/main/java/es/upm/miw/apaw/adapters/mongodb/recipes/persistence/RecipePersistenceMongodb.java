package es.upm.miw.apaw.adapters.mongodb.recipes.persistence;

import es.upm.miw.apaw.adapters.mongodb.recipes.daos.RecipeRepository;
import es.upm.miw.apaw.adapters.mongodb.recipes.entities.RecipeEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recipes.Recipe;
import es.upm.miw.apaw.domain.persistenceports.recipes.RecipePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("recipePersistence")
public class RecipePersistenceMongodb implements RecipePersistence {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipePersistenceMongodb(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe readByReferenceNumber(String referenceNumber) {
        return this.recipeRepository.readByReferenceNumber(referenceNumber)
                .orElseThrow(() -> new NotFoundException(" Recipe reference number: " + referenceNumber))
                .toRecipe();
    }

    @Override
    public void delete(String referenceNumber) {
        this.recipeRepository.deleteByReferenceNumber(referenceNumber);
    }

    @Override
    public Stream<Recipe> readAll() {
        return this.recipeRepository.findAll().stream()
                .map(RecipeEntity::toRecipe);
    }
}
