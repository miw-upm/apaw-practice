package es.upm.miw.apaw.domain.model.legalprocedure;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record LegalTaskStatusUpdate(@NotNull UUID id, @NotNull TaskStatus taskStatus) {
}
