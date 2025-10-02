package es.upm.miw.apaw.domain.models.recipes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    @NotNull
    @NotBlank
    private String catalogCode;
    @NotNull
    @NotBlank
    private String label;
    private String measurementUnit;
    private Double quantity;
    private Double energyKcal;
    private BigDecimal marketPrice;
    private List<RecipeIngredient> recipeIngredients;
}
