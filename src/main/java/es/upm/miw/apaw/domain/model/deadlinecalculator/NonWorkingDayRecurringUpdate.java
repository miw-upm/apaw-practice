package es.upm.miw.apaw.domain.model.deadlinecalculator;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NonWorkingDayRecurringUpdate(@NotNull UUID id, @NotNull Boolean recurring) {
}
