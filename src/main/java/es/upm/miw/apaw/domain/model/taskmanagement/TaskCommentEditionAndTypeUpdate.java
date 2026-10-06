package es.upm.miw.apaw.domain.model.taskmanagement;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TaskCommentEditionAndTypeUpdate(
        @NotNull UUID id,
        @NotNull Boolean edition,
        @NotNull CommentType type
) {
}
