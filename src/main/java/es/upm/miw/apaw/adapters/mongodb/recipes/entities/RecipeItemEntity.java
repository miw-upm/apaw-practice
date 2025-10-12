package es.upm.miw.apaw.adapters.mongodb.recipes.entities;

import es.upm.miw.apaw.domain.models.recipes.RecipeItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeItemEntity {
    @DBRef
    private IngredientEntity ingredientEntity;
    private Double quantity;
    private String specifications;
    private Boolean optional;

    public RecipeItem toRecipeItem() {
        RecipeItem recipeItem = new RecipeItem();
        BeanUtils.copyProperties(this, recipeItem, "ingredientEntity");
        recipeItem.setIngredient(this.ingredientEntity.toIngredient());
        return recipeItem;
    }
}
