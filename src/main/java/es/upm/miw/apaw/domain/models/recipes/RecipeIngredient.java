package es.upm.miw.apaw.domain.models.recipes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredient {
    private Recipe recipe;
    private Ingredient ingredient;
    private Double usedQuantity;
}
