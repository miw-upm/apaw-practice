package es.upm.miw.apaw.domain.models.recipes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @NotNull
    @NotBlank
    private String referenceNumber;
    @NotNull
    @NotBlank
    private String title;
    private String instructions;
    private Integer servings;
    private List<RecipeItem> items;
}
