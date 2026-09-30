package es.upm.miw.apaw.domain.ports.out.notifications;

import es.upm.miw.apaw.domain.model.notifications.Notification;

public interface NotificationGateway {
    Notification create(Notification notification);
}
