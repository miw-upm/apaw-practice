package es.upm.miw.apaw.domain.ports.out.notifications;

import es.upm.miw.apaw.domain.model.notifications.Notification;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplateFailureReport;

import java.util.List;

public interface NotificationGateway {
    Notification create(Notification notification);

    List<NotificationTemplateFailureReport> findTemplateFailureReport();
}
