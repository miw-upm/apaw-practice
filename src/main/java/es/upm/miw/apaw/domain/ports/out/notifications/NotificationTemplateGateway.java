package es.upm.miw.apaw.domain.ports.out.notifications;

import es.upm.miw.apaw.domain.model.notifications.NotificationTemplate;

import java.util.Optional;
import java.util.UUID;

public interface NotificationTemplateGateway {
    NotificationTemplate create(NotificationTemplate notificationTemplate);

    NotificationTemplate update(NotificationTemplate notificationTemplate);

    boolean existsByEventType(String eventType);

    Optional<NotificationTemplate> read(UUID id);
}
