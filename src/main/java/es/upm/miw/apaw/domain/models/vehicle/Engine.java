package es.upm.miw.apaw.domain.models.vehicle;

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
public class Engine {
    @NotNull
    @NotBlank
    private String codeEngine;
    @NotNull
    @NotBlank
    private String type;
    private Double displacement;
}
