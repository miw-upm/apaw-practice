package es.upm.miw.apaw.adapters.in.notifications;

import es.upm.miw.apaw.domain.model.notifications.CreationNotification;
import es.upm.miw.apaw.domain.model.notifications.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreationNotificationDto(
        @NotBlank String title,
        @NotBlank String message,
        @NotNull UUID notificationTemplateId,
        Priority priority,
        @NotNull UUID userId) {

    public CreationNotification toDomain() {
        return CreationNotification.builder()
                .title(this.title)
                .message(this.message)
                .notificationTemplateId(this.notificationTemplateId)
                .priority(this.priority)
                .userId(this.userId)
                .build();
    }
}
