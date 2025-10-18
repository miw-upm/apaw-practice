package es.upm.miw.apaw.domain.models.martialartsgym;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Equipment {

    @EqualsAndHashCode.Include
    @NotNull
    private Integer barCode;

    @NotNull
    @NotBlank
    private String itemLabel;

    @NotNull
    private BigDecimal unitCost;
}
