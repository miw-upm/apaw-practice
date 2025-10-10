package es.upm.miw.apaw.adapters.resources.recipes;

import es.upm.miw.apaw.domain.services.recipes.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RecipeResource.RECIPES)
public class RecipeResource {
    public static final String RECIPES = "/recipes/recipe";
    public static final String REFERENCE_NUMBER =  "/{referenceNumber}";

    private final RecipeService recipeService;

    @Autowired
    public RecipeResource(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @DeleteMapping(REFERENCE_NUMBER)
    public void delete(@PathVariable String referenceNumber) {
        this.recipeService.delete(referenceNumber);
    }
}
