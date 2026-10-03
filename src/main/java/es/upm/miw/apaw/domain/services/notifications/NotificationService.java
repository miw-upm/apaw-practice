package es.upm.miw.apaw.domain.services.notifications;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.notifications.CreationNotification;
import es.upm.miw.apaw.domain.model.notifications.Notification;
import es.upm.miw.apaw.domain.model.notifications.NotificationFindCriteria;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplate;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplateFailureReport;
import es.upm.miw.apaw.domain.ports.out.notifications.NotificationGateway;
import es.upm.miw.apaw.domain.ports.out.notifications.NotificationTemplateGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationGateway notificationGateway;
    private final NotificationTemplateGateway notificationTemplateGateway;
    private final UserFinder userFinder;

    public List<NotificationTemplateFailureReport> findTemplateFailureReport() {
        return this.notificationGateway.findTemplateFailureReport();
    }

    public List<Notification> find(NotificationFindCriteria criteria) {
        List<Notification> notifications = this.notificationGateway.find(criteria);
        if (notifications.isEmpty()) {
            return List.of();
        }
        Map<UUID, UserSnapshot> usersById = this.findUsersById(notifications);
        return notifications.stream()
                .map(notification -> this.enrichRecipient(notification, usersById))
                .filter(notification -> this.matchesRecipientEmail(criteria, notification))
                .toList();
    }

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

    private Map<UUID, UserSnapshot> findUsersById(List<Notification> notifications) {
        Set<UUID> userIds = notifications.stream()
                .map(notification -> notification.getRecipient().getId())
                .collect(Collectors.toSet());
        return this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
    }

    private Notification enrichRecipient(Notification notification, Map<UUID, UserSnapshot> usersById) {
        UUID userId = notification.getRecipient().getId();
        UserSnapshot recipient = usersById.get(userId);
        if (recipient == null) {
            throw new NotFoundException("User id not found: " + userId);
        }
        notification.setRecipient(recipient);
        return notification;
    }

    private boolean matchesRecipientEmail(NotificationFindCriteria criteria, Notification notification) {
        return !criteria.hasRecipientEmail()
                || criteria.getRecipientEmail().equals(notification.getRecipient().getEmail());
    }
}
