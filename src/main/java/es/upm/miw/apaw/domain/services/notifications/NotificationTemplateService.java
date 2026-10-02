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

        this.validate(update);
        this.assertEventTypeIsAvailable(update.getEventType(), storedTemplate.getEventType());

        storedTemplate.setEventType(update.getEventType());
        storedTemplate.setSubjectTemplate(update.getSubjectTemplate());
        storedTemplate.setBodyTemplate(update.getBodyTemplate());
        storedTemplate.setChannel(update.getChannel());

        return this.notificationTemplateGateway.update(storedTemplate);
    }

    public NotificationTemplate patch(UUID id, NotificationTemplate patch) {
        NotificationTemplate storedTemplate = this.read(id);
        String currentEventType = storedTemplate.getEventType();

        Optional.ofNullable(patch.getEventType()).ifPresent(storedTemplate::setEventType);
        Optional.ofNullable(patch.getSubjectTemplate()).ifPresent(storedTemplate::setSubjectTemplate);
        Optional.ofNullable(patch.getBodyTemplate()).ifPresent(storedTemplate::setBodyTemplate);
        Optional.ofNullable(patch.getChannel()).ifPresent(storedTemplate::setChannel);

        this.validate(storedTemplate);
        this.assertEventTypeIsAvailable(storedTemplate.getEventType(), currentEventType);
        return this.notificationTemplateGateway.update(storedTemplate);
    }

    public void delete(UUID id) {
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
        if (notificationTemplate.getChannel() == null) {
            throw new BadRequestException("Notification template channel must not be null");
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

    private void assertEventTypeIsAvailable(String eventType, String currentEventType) {
        if (!eventType.equals(currentEventType)) {
            this.assertEventTypeIsAvailable(eventType);
        }
    }
}
