package es.upm.miw.apaw.domain.models.fighters;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Fighter {
    @NotBlank
    private String nickname;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String country;
    @NotNull
    private Double weight;
    @NotNull
    private Double height;
    @Min(0)
    private int wins;
    @Min(0)
    private int losses;
}
