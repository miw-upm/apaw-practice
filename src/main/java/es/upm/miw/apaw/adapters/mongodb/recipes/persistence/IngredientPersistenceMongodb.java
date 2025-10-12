package es.upm.miw.apaw.adapters.mongodb.recipes.persistence;

import es.upm.miw.apaw.adapters.mongodb.recipes.daos.IngredientRepository;
import es.upm.miw.apaw.adapters.mongodb.recipes.entities.IngredientEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recipes.Ingredient;
import es.upm.miw.apaw.domain.models.recipes.Recipe;
import es.upm.miw.apaw.domain.persistenceports.recipes.IngredientPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("ingredientPersistence")
public class IngredientPersistenceMongodb implements IngredientPersistence {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientPersistenceMongodb(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient readByLabel(String label) {
        return this.ingredientRepository.readByLabel(label)
                .orElseThrow(() -> new NotFoundException(" Ingredient label: " + label))
                .toIngredient();
    }
}
