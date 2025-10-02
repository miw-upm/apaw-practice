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
public class Origin {
    @NotNull
    @NotBlank
    private String isoCode;
    @NotNull
    @NotBlank
    private String countryName;
    private String culturalRegion;
    private List<Recipe> recipes;
}
