package es.upm.miw.apaw.adapters.out.notifications.postgres;

import es.upm.miw.apaw.domain.model.notifications.Channel;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NotificationTemplateEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true, length = 60)
    private String eventType;

    @Column(nullable = false, length = 60)
    private String subjectTemplate;

    @Column(nullable = false, length = 500)
    private String bodyTemplate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Channel channel;

    public NotificationTemplateEntity(NotificationTemplate notificationTemplate) {
        BeanUtils.copyProperties(notificationTemplate, this);
    }

    public NotificationTemplate toDomain() {
        NotificationTemplate notificationTemplate = new NotificationTemplate();
        BeanUtils.copyProperties(this, notificationTemplate);
        return notificationTemplate;
    }
}
