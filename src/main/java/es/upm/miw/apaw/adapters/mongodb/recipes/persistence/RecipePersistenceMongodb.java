package es.upm.miw.apaw.adapters.mongodb.recipes.persistence;

import es.upm.miw.apaw.adapters.mongodb.recipes.daos.IngredientRepository;
import es.upm.miw.apaw.adapters.mongodb.recipes.daos.RecipeRepository;
import es.upm.miw.apaw.adapters.mongodb.recipes.entities.IngredientEntity;
import es.upm.miw.apaw.adapters.mongodb.recipes.entities.RecipeEntity;
import es.upm.miw.apaw.adapters.mongodb.recipes.entities.RecipeItemEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recipes.Recipe;
import es.upm.miw.apaw.domain.persistenceports.recipes.RecipePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository("recipePersistence")
public class RecipePersistenceMongodb implements RecipePersistence {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public RecipePersistenceMongodb(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
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

    @Override
    public Recipe create(Recipe recipe) {
        RecipeEntity recipeEntity = new RecipeEntity(recipe);
        recipeEntity.setItemEntities(recipe.getItems().stream()
                .map(recipeItem -> {
                    RecipeItemEntity recipeItemEntity = new RecipeItemEntity(recipeItem);
                    IngredientEntity ingredientEntity = ingredientRepository.readByLabel(recipeItem.getIngredient().getLabel())
                            .orElseThrow(() -> new NotFoundException(
                                    "Ingredient label: " + recipeItem.getIngredient().getLabel()
                            ));

                    recipeItemEntity.setIngredientEntity(ingredientEntity);
                    return recipeItemEntity;
                })
                .toList()
        );
        return this.recipeRepository.save(recipeEntity).toRecipe();
    }

    @Override
    public Recipe update(Recipe recipe) {
        RecipeEntity recipeEntity = this.recipeRepository
                .readByReferenceNumber(recipe.getReferenceNumber())
                .orElseThrow(() -> new NotFoundException(" Recipe reference number: " + recipe.getReferenceNumber()));
        List<RecipeItemEntity> recipeItemEntities = recipe.getItems().stream()
                .map(recipeItem -> new RecipeItemEntity(
                        this.ingredientRepository
                                .readByLabel(recipeItem.getIngredient().getLabel())
                                .orElseThrow(() -> new NotFoundException(
                                        "Ingredient label: " + recipeItem.getIngredient().getLabel()
                                )),
                        recipeItem.getQuantity(),
                        recipeItem.getSpecifications(),
                        recipeItem.getOptional())
                ).toList();
        recipeEntity.setItemEntities(recipeItemEntities);
        return this.recipeRepository.save(recipeEntity).toRecipe();
    }
}
