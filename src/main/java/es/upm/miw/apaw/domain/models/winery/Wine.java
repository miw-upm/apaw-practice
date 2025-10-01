package es.upm.miw.apaw.domain.models.winery;

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
public class Wine {
    @NotNull
    @NotBlank
    private Long idWine;
    private String name;
    private Integer year;
    private Double alcoholPercentage;
    private BigDecimal price;
}
