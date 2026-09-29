package es.upm.miw.apaw.domain.services.notifications;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplate;
import es.upm.miw.apaw.domain.ports.out.notifications.NotificationTemplateGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationTemplateService {
    private final NotificationTemplateGateway notificationTemplateGateway;

    public NotificationTemplate create(NotificationTemplate notificationTemplate) {
        if (this.notificationTemplateGateway.existsByEventType(notificationTemplate.getEventType())) {
            throw new ConflictException(
                    "Notification template event type already exists: " + notificationTemplate.getEventType());
        }
        notificationTemplate.doDefault();
        return this.notificationTemplateGateway.create(notificationTemplate);
    }
}
