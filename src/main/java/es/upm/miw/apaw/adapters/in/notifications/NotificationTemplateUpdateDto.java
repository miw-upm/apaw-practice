package es.upm.miw.apaw.adapters.in.notifications;

import es.upm.miw.apaw.domain.model.notifications.Channel;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplateUpdate;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NotificationTemplateUpdateDto(
        @Pattern(regexp = ".*\\S.*")
        String eventType,
        @Pattern(regexp = ".*\\S.*")
        @Size(max = 60, message = "subjectTemplate must not exceed 60 characters")
        String subjectTemplate,
        @Pattern(regexp = ".*\\S.*")
        @Size(max = 500, message = "bodyTemplate must not exceed 500 characters")
        String bodyTemplate,
        Channel channel
) {
    public NotificationTemplateUpdate toDomain() {
        return new NotificationTemplateUpdate(
                this.eventType,
                this.subjectTemplate,
                this.bodyTemplate,
                this.channel
        );
    }
}
