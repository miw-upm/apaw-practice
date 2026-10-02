package es.upm.miw.apaw.domain.model.copyright;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ClaimTaskStatusUpdate(
        @NotNull UUID id,
        @NotNull TaskStatus taskStatus
) {
}
