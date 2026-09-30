package es.upm.miw.apaw.domain.services.notifications;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplate;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplateUpdate;
import es.upm.miw.apaw.domain.ports.out.notifications.NotificationTemplateGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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

    public NotificationTemplate read(UUID id) {
        return this.notificationTemplateGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Notification template id not found: " + id));
    }

    public NotificationTemplate update(UUID id, NotificationTemplateUpdate update) {
        NotificationTemplate storedTemplate = this.read(id);
        if (update.eventType() != null
                && !update.eventType().equals(storedTemplate.getEventType())
                && this.notificationTemplateGateway.existsByEventType(update.eventType())) {
            throw new ConflictException("Notification template event type already exists: " + update.eventType());
        }
        Optional.ofNullable(update.eventType()).ifPresent(storedTemplate::setEventType);
        Optional.ofNullable(update.subjectTemplate()).ifPresent(storedTemplate::setSubjectTemplate);
        Optional.ofNullable(update.bodyTemplate()).ifPresent(storedTemplate::setBodyTemplate);
        Optional.ofNullable(update.channel()).ifPresent(storedTemplate::setChannel);
        return this.notificationTemplateGateway.update(storedTemplate);
    }
}
