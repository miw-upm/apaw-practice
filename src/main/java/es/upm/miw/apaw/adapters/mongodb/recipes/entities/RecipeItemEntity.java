package es.upm.miw.apaw.adapters.mongodb.recipes.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
}
