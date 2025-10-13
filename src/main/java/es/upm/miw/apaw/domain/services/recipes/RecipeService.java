package es.upm.miw.apaw.domain.services.recipes;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recipes.Recipe;
import es.upm.miw.apaw.domain.models.recipes.RecipeItem;
import es.upm.miw.apaw.domain.persistenceports.recipes.RecipePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Stream;

@Service
public class RecipeService {
    private final RecipePersistence recipePersistence;

    @Autowired
    public RecipeService(RecipePersistence recipePersistence) {
        this.recipePersistence = recipePersistence;
    }

    public Recipe read(String referenceNumber) {
        return this.recipePersistence.readByReferenceNumber(referenceNumber);
    }

    public void delete(String referenceNumber) {
        this.recipePersistence.delete(referenceNumber);
    }

    public Stream<Recipe> readAll() {
        return this.recipePersistence.readAll();
    }

    public Recipe create(@Valid Recipe recipe) {
        try {
            this.read(recipe.getReferenceNumber());
            throw new ConflictException(
                    "A recipe with reference number '" + recipe.getReferenceNumber() + "' already exists."
            );
        } catch (NotFoundException e) {
            return recipePersistence.create(recipe);
        }
    }

    public Recipe updateItems(@Valid String referenceNumber, List<RecipeItem> recipeItemsList) {
        Recipe recipe = this.recipePersistence.readByReferenceNumber(referenceNumber);
        recipe.setItems(recipeItemsList);
        return this.recipePersistence.update(recipe);
    }
}
