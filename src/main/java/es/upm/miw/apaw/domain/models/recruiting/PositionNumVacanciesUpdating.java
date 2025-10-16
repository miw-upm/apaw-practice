package es.upm.miw.apaw.domain.models.recruiting;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionNumVacanciesUpdating {
    @NotNull
    private Integer reference;
    @NotNull
    private int numVacancies;
}