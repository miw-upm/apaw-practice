package es.upm.miw.apaw.adapters.out.notifications.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.notifications.Notification;
import es.upm.miw.apaw.domain.model.notifications.NotificationStatus;
import es.upm.miw.apaw.domain.model.notifications.Priority;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NotificationEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private LocalDate createdAt;

    private LocalDate sentAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "notification_template_id", nullable = false)
    private NotificationTemplateEntity notificationTemplate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationStatus notificationStatus;

    @Column(nullable = false)
    private UUID recipientId;

    public NotificationEntity(Notification notification) {
        BeanUtils.copyProperties(notification, this, "notificationTemplate", "recipient");
        this.notificationTemplate = new NotificationTemplateEntity(notification.getNotificationTemplate());
        this.recipientId = notification.getRecipient().getId();
    }

    public Notification toDomain() {
        Notification notification = new Notification();
        BeanUtils.copyProperties(this, notification, "notificationTemplate", "recipientId");
        notification.setNotificationTemplate(this.notificationTemplate.toDomain());
        notification.setRecipient(UserSnapshot.builder().id(this.recipientId).build());
        return notification;
    }
}
