package es.upm.miw.apaw.adapters.out.notifications.postgres;

import es.upm.miw.apaw.domain.model.notifications.Notification;
import es.upm.miw.apaw.domain.model.notifications.NotificationFindCriteria;
import es.upm.miw.apaw.domain.model.notifications.NotificationStatus;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplateFailureReport;
import es.upm.miw.apaw.domain.ports.out.notifications.NotificationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotificationAdapter implements NotificationGateway {
    private final NotificationRepository notificationRepository;
    private final NotificationTemplateRepository notificationTemplateRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Notification> find(NotificationFindCriteria criteria) {
        return this.notificationRepository.findAll(this.buildSpecification(criteria)).stream()
                .map(NotificationEntity::toDomain)
                .toList();
    }

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

    private Specification<NotificationEntity> buildSpecification(NotificationFindCriteria criteria) {
        Specification<NotificationEntity> specification = (root, query, builder) -> builder.conjunction();
        if (criteria.hasPriority()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("priority"), criteria.getPriority()));
        }
        if (criteria.hasSent()) {
            specification = specification.and((root, query, builder) -> {
                var sentPredicate = builder.and(
                        builder.isNotNull(root.get("sentAt")),
                        builder.equal(root.get("notificationStatus"), NotificationStatus.DELIVERED));
                return criteria.getSent() ? sentPredicate : builder.not(sentPredicate);
            });
        }
        if (criteria.hasEventType()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.join("notificationTemplate").get("eventType"), criteria.getEventType()));
        }
        return specification;
    }
}
