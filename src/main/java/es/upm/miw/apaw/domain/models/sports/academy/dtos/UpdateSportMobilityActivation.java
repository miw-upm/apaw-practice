package es.upm.miw.apaw.domain.models.sports.academy.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSportMobilityActivation {
    @NotNull
    private boolean active;
}
