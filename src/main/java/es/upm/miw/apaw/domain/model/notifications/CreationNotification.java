package es.upm.miw.apaw.domain.model.notifications;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreationNotification {
    private String title;

    private String message;

    private UUID notificationTemplateId;

    private Priority priority;

    private UUID userId;
}
