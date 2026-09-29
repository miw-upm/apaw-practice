package es.upm.miw.apaw.domain.ports.out.notifications;

import es.upm.miw.apaw.domain.model.notifications.NotificationTemplate;

public interface NotificationTemplateGateway {
    NotificationTemplate create(NotificationTemplate notificationTemplate);

    boolean existsByEventType(String eventType);
}
