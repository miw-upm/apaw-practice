package es.upm.miw.apaw.adapters.out.notifications.postgres;

import es.upm.miw.apaw.domain.model.notifications.NotificationTemplate;
import es.upm.miw.apaw.domain.ports.out.notifications.NotificationTemplateGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class NotificationTemplateAdapter implements NotificationTemplateGateway {
    private final NotificationTemplateRepository notificationTemplateRepository;

    @Override
    public NotificationTemplate create(NotificationTemplate notificationTemplate) {
        return this.notificationTemplateRepository
                .save(new NotificationTemplateEntity(notificationTemplate))
                .toDomain();
    }

    @Override
    public boolean existsByEventType(String eventType) {
        return this.notificationTemplateRepository.existsByEventType(eventType);
    }

    @Override
    public Optional<NotificationTemplate> read(UUID id) {
        return this.notificationTemplateRepository.findById(id)
                .map(NotificationTemplateEntity::toDomain);
    }
}
