package es.upm.miw.apaw.adapters.resources.recipes;

import es.upm.miw.apaw.domain.models.recipes.Ingredient;
import es.upm.miw.apaw.domain.services.recipes.IngredientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(IngredientResource.INGREDIENTS)

public class IngredientResource {
    public static final String INGREDIENTS = "/recipes/ingredients";

    private final IngredientService ingredientService;

    @Autowired
    public IngredientResource(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PatchMapping
    public void updateIngredient(@Valid @RequestBody List<Ingredient> ingredients) {
        this.ingredientService.updatePrices(ingredients.stream());
    }
}
