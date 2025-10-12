package es.upm.miw.apaw.domain.persistenceports.recipes;

import es.upm.miw.apaw.domain.models.recipes.Ingredient;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientPersistence {

    Ingredient readByLabel(String label);
}
