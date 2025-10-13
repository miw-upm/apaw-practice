package es.upm.miw.apaw.domain.services.recipes;

import es.upm.miw.apaw.domain.models.recipes.Ingredient;
import es.upm.miw.apaw.domain.persistenceports.recipes.IngredientPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class IngredientService {
    private final IngredientPersistence ingredientPersistence;

    @Autowired
    public IngredientService(IngredientPersistence ingredientPersistence) {
        this.ingredientPersistence = ingredientPersistence;
    }

    public void updatePrices(Stream<Ingredient> ingredientList) {
        ingredientList.map(ingredientNewPrice -> {
                    Ingredient ingredient = this.ingredientPersistence.readByLabel(ingredientNewPrice.getLabel());
                    ingredient.setMarketPrice(ingredientNewPrice.getMarketPrice());
                    return ingredient;
                })
                .forEach(this.ingredientPersistence::update);
    }
}
