package es.upm.miw.apaw.adapters.out.notifications.postgres;

import es.upm.miw.apaw.domain.model.notifications.Notification;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplateFailureReport;
import es.upm.miw.apaw.domain.ports.out.notifications.NotificationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotificationAdapter implements NotificationGateway {
    private final NotificationRepository notificationRepository;
    private final NotificationTemplateRepository notificationTemplateRepository;

    @Override
    public List<NotificationTemplateFailureReport> findTemplateFailureReport() {
        return this.notificationRepository.findTemplateFailureReport();
    }

    @Override
    @Transactional
    public Notification create(Notification notification) {
        NotificationEntity entity = new NotificationEntity(notification);
        entity.setNotificationTemplate(this.notificationTemplateRepository.getReferenceById(
                notification.getNotificationTemplate().getId()));
        this.notificationRepository.save(entity);
        return notification;
    }
}
