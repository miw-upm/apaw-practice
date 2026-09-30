package es.upm.miw.apaw.domain.services.notifications;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.notifications.CreationNotification;
import es.upm.miw.apaw.domain.model.notifications.Notification;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplate;
import es.upm.miw.apaw.domain.ports.out.notifications.NotificationGateway;
import es.upm.miw.apaw.domain.ports.out.notifications.NotificationTemplateGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationGateway notificationGateway;
    private final NotificationTemplateGateway notificationTemplateGateway;
    private final UserFinder userFinder;

    public Notification create(CreationNotification creation) {
        NotificationTemplate notificationTemplate = this.notificationTemplateGateway
                .read(creation.getNotificationTemplateId())
                .orElseThrow(() -> new NotFoundException(
                        "Notification template id not found: " + creation.getNotificationTemplateId()));
        UserSnapshot recipient = this.userFinder.read(creation.getUserId());
        if (recipient == null) {
            throw new NotFoundException("User id not found: " + creation.getUserId());
        }
        Notification notification = new Notification();
        BeanUtils.copyProperties(creation, notification, "notificationTemplateId", "userId");
        notification.setNotificationTemplate(notificationTemplate);
        notification.setRecipient(recipient);
        notification.doDefault();
        return this.notificationGateway.create(notification);
    }
}
