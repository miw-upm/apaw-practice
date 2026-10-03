package es.upm.miw.apaw.domain.ports.out.notifications;

import es.upm.miw.apaw.domain.model.notifications.NotificationTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationTemplateGateway {
    NotificationTemplate create(NotificationTemplate notificationTemplate);

    NotificationTemplate update(NotificationTemplate notificationTemplate);

    void delete(UUID id);

    boolean isReferenced(UUID id);

    boolean existsByEventType(String eventType);

    Optional<NotificationTemplate> read(UUID id);

    List<NotificationTemplate> findAll();
}
