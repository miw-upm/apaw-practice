package es.upm.miw.apaw.domain.models.recipes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    @NotNull
    @NotBlank
    private String label;
    private String measurementUnit;
    private Double unitQuantity;
    private BigDecimal marketPrice;
}
