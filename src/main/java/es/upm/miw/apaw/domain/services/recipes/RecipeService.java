package es.upm.miw.apaw.domain.services.recipes;

import es.upm.miw.apaw.domain.models.recipes.Recipe;
import es.upm.miw.apaw.domain.persistenceports.recipes.RecipePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
