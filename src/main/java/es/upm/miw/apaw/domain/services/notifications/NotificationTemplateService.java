package es.upm.miw.apaw.domain.services.notifications;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplate;
import es.upm.miw.apaw.domain.ports.out.notifications.NotificationTemplateGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationTemplateService {
    private final NotificationTemplateGateway notificationTemplateGateway;

    public NotificationTemplate create(NotificationTemplate notificationTemplate) {
        this.validateEventType(notificationTemplate.getEventType());
        this.assertEventTypeIsAvailable(notificationTemplate.getEventType());
        notificationTemplate.doDefault();
        return this.notificationTemplateGateway.create(notificationTemplate);
    }

    public NotificationTemplate read(UUID id) {
        return this.notificationTemplateGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Notification template id not found: " + id));
    }

    public List<NotificationTemplate> findAll() {
        return this.notificationTemplateGateway.findAll();
    }

    public NotificationTemplate update(UUID id, NotificationTemplate update) {
        NotificationTemplate storedTemplate = this.read(id);
        String storedEventType = storedTemplate.getEventType();

        Optional.ofNullable(update.getEventType()).ifPresent(storedTemplate::setEventType);
        Optional.ofNullable(update.getSubjectTemplate()).ifPresent(storedTemplate::setSubjectTemplate);
        Optional.ofNullable(update.getBodyTemplate()).ifPresent(storedTemplate::setBodyTemplate);
        Optional.ofNullable(update.getChannel()).ifPresent(storedTemplate::setChannel);

        this.validate(storedTemplate);

        if (!storedTemplate.getEventType().equals(storedEventType)) {
            this.assertEventTypeIsAvailable(storedTemplate.getEventType());
        }
        return this.notificationTemplateGateway.update(storedTemplate);
    }

    public void delete(UUID id) {
        this.read(id);
        if (this.notificationTemplateGateway.isReferenced(id)) {
            throw new ConflictException("Notification template is referenced by a notification: " + id);
        }
        this.notificationTemplateGateway.delete(id);
    }

    private void validate(NotificationTemplate notificationTemplate) {
        this.validateEventType(notificationTemplate.getEventType());
        if (notificationTemplate.getSubjectTemplate() == null
                || notificationTemplate.getSubjectTemplate().isBlank()) {
            throw new BadRequestException("Notification template subject must not be blank");
        }
        if (notificationTemplate.getSubjectTemplate().length() > 60) {
            throw new BadRequestException("Notification template subject must not exceed 60 characters");
        }
        if (notificationTemplate.getBodyTemplate() == null || notificationTemplate.getBodyTemplate().isBlank()) {
            throw new BadRequestException("Notification template body must not be blank");
        }
        if (notificationTemplate.getBodyTemplate().length() > 500) {
            throw new BadRequestException("Notification template body must not exceed 500 characters");
        }
    }

    private void validateEventType(String eventType) {
        if (eventType == null || eventType.isBlank()) {
            throw new BadRequestException("Notification template event type must not be blank");
        }
        if (eventType.length() > 60) {
            throw new BadRequestException("Notification template event type must not exceed 60 characters");
        }
    }

    private void assertEventTypeIsAvailable(String eventType) {
        if (this.notificationTemplateGateway.existsByEventType(eventType)) {
            throw new ConflictException("Notification template event type already exists: " + eventType);
        }
    }
}
