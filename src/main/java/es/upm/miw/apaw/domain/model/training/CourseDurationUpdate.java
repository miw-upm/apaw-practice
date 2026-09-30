package es.upm.miw.apaw.domain.model.training;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CourseDurationUpdate(@NotNull UUID id, @NotNull Integer durationHours) {
}
