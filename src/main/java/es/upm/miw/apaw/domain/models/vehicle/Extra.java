package es.upm.miw.apaw.domain.models.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Extra {
    @NotNull
    @NotBlank
    private String category;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal price;
}
