package es.upm.miw.apaw.domain.models.sports.academy.dtos;

import es.upm.miw.apaw.domain.models.sports.academy.enums.Level;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SportModalitiesLevelsPercentage {
    @NotNull
    private Level level;
    @NotNull
    private Double percentage;
}
