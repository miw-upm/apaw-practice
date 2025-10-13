package es.upm.miw.apaw.adapters.resources.recipes;

import es.upm.miw.apaw.domain.models.recipes.Recipe;
import es.upm.miw.apaw.domain.models.recipes.RecipeItem;
import es.upm.miw.apaw.domain.services.recipes.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RecipeResource.RECIPES)
public class RecipeResource {
    public static final String RECIPES = "/recipes/recipe";
    public static final String REFERENCE_NUMBER =  "/{referenceNumber}";
    public static final String RECIPE_ITEMS = "/recipe-items";

    private final RecipeService recipeService;

    @Autowired
    public RecipeResource(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @DeleteMapping(REFERENCE_NUMBER)
    public void delete(@PathVariable String referenceNumber) {
        this.recipeService.delete(referenceNumber);
    }

    @PostMapping
    public Recipe create(@Valid @RequestBody Recipe recipe) {
        return this.recipeService.create(recipe);
    }

    @PutMapping(REFERENCE_NUMBER + RECIPE_ITEMS)
    public Recipe updateItems(@Valid @PathVariable String referenceNumber, @RequestBody List<RecipeItem> recipeItemsList) {
        return this.recipeService.updateItems(referenceNumber, recipeItemsList);
    }
}
