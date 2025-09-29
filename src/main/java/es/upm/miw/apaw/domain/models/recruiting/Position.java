package es.upm.miw.apaw.domain.models.recruiting;

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
public class Position {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private BigDecimal annualSalary;
    private BigDecimal bonusSalary;
    @NotNull
    @NotBlank
    private Integer numVacancies;
}