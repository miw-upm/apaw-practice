package es.upm.miw.apaw.adapters.out.notifications.postgres;

import es.upm.miw.apaw.domain.model.notifications.NotificationTemplateFailureReport;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<NotificationEntity, UUID>,
        JpaSpecificationExecutor<NotificationEntity> {
    boolean existsByNotificationTemplateId(UUID notificationTemplateId);

    @Override
    @EntityGraph(attributePaths = "notificationTemplate")
    List<NotificationEntity> findAll(Specification<NotificationEntity> specification);

    @Query("""
            select new es.upm.miw.apaw.domain.model.notifications.NotificationTemplateFailureReport(
                notification.notificationTemplate.eventType,
                notification.notificationTemplate.channel,
                count(notification),
                sum(case when notification.notificationStatus =
                    es.upm.miw.apaw.domain.model.notifications.NotificationStatus.FAILED then 1L else 0L end),
                100.0 * sum(case when notification.notificationStatus =
                    es.upm.miw.apaw.domain.model.notifications.NotificationStatus.FAILED then 1L else 0L end)
                    / count(notification)
            )
            from NotificationEntity notification
            join notification.notificationTemplate template
            group by template.eventType, template.channel
            order by 100.0 * sum(case when notification.notificationStatus =
                es.upm.miw.apaw.domain.model.notifications.NotificationStatus.FAILED then 1L else 0L end)
                / count(notification) desc,
                sum(case when notification.notificationStatus =
                    es.upm.miw.apaw.domain.model.notifications.NotificationStatus.FAILED then 1L else 0L end) desc,
                template.eventType asc,
                template.channel asc
            """)
    List<NotificationTemplateFailureReport> findTemplateFailureReport();
}
