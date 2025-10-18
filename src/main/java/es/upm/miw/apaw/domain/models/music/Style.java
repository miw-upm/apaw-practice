package es.upm.miw.apaw.domain.models.music;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Style {
    @NotNull
    @NotBlank
    private String genre;
    @Min(0) @Max(100)
    private Integer popularityIndex;
    @NotNull
    @NotBlank
    private String mood;
}