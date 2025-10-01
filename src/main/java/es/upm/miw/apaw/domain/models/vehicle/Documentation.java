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
public class Documentation {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private Boolean validate;
}
