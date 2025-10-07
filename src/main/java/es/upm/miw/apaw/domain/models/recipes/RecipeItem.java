package es.upm.miw.apaw.domain.models.recipes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeItem {
    private Ingredient ingredient;
    private Double quantity;
    private String specifications;
    private Boolean optional;
}
