package es.upm.miw.apaw.domain.model.meeting;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record LegalIssueResolvedUpdate(@NotNull UUID id, @NotNull Boolean resolved) {
}
