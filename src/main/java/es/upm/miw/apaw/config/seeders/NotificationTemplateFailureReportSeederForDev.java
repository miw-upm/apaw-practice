package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.notifications.postgres.NotificationEntity;
import es.upm.miw.apaw.adapters.out.notifications.postgres.NotificationRepository;
import es.upm.miw.apaw.adapters.out.notifications.postgres.NotificationTemplateEntity;
import es.upm.miw.apaw.adapters.out.notifications.postgres.NotificationTemplateRepository;
import es.upm.miw.apaw.domain.model.notifications.Channel;
import es.upm.miw.apaw.domain.model.notifications.NotificationStatus;
import es.upm.miw.apaw.domain.model.notifications.Priority;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(5)
@RequiredArgsConstructor
public class NotificationTemplateFailureReportSeederForDev implements ApplicationRunner {
    private static final String ID_PREFIX = "eeeeeeee-aaaa-bbbb-cccc-ddddeeee";
    public static final String EVENT_TYPE_PREFIX = "DEMO_FAILURE_REPORT_";
    public static final String MOST_FAILED_EVENT_TYPE = EVENT_TYPE_PREFIX + "MOST_FAILED";
    public static final String SECOND_EVENT_TYPE = EVENT_TYPE_PREFIX + "SECOND";
    public static final String NO_FAILURE_EVENT_TYPE = EVENT_TYPE_PREFIX + "NONE";

    private static final UUID MOST_FAILED_TEMPLATE_ID = UUID.fromString(ID_PREFIX + "0001");
    private static final UUID SECOND_TEMPLATE_ID = UUID.fromString(ID_PREFIX + "0002");
    private static final UUID NO_FAILURE_TEMPLATE_ID = UUID.fromString(ID_PREFIX + "0003");

    private static final List<UUID> NOTIFICATION_IDS = List.of(
            UUID.fromString(ID_PREFIX + "1001"),
            UUID.fromString(ID_PREFIX + "1002"),
            UUID.fromString(ID_PREFIX + "1003"),
            UUID.fromString(ID_PREFIX + "2001"),
            UUID.fromString(ID_PREFIX + "2002"),
            UUID.fromString(ID_PREFIX + "3001"));

    private final NotificationRepository notificationRepository;
    private final NotificationTemplateRepository notificationTemplateRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        this.seedTemplates();
        this.seedNotifications();
    }

    private void seedTemplates() {
        List<NotificationTemplateEntity> templates = List.of(
                this.createTemplate(MOST_FAILED_TEMPLATE_ID, MOST_FAILED_EVENT_TYPE, Channel.EMAIL),
                this.createTemplate(SECOND_TEMPLATE_ID, SECOND_EVENT_TYPE, Channel.SMS),
                this.createTemplate(NO_FAILURE_TEMPLATE_ID, NO_FAILURE_EVENT_TYPE, Channel.PUSH))
                .stream()
                .filter(template -> !this.notificationTemplateRepository.existsById(template.getId()))
                .toList();
        this.notificationTemplateRepository.saveAll(templates);
        log.info("Notification failure report templates seeded: {}", templates.size());
    }

    private void seedNotifications() {
        NotificationTemplateEntity mostFailedTemplate =
                this.notificationTemplateRepository.getReferenceById(MOST_FAILED_TEMPLATE_ID);
        NotificationTemplateEntity secondTemplate =
                this.notificationTemplateRepository.getReferenceById(SECOND_TEMPLATE_ID);
        NotificationTemplateEntity noFailureTemplate =
                this.notificationTemplateRepository.getReferenceById(NO_FAILURE_TEMPLATE_ID);
        List<NotificationEntity> notifications = List.of(
                this.createNotification(NOTIFICATION_IDS.get(0), mostFailedTemplate, NotificationStatus.FAILED),
                this.createNotification(NOTIFICATION_IDS.get(1), mostFailedTemplate, NotificationStatus.FAILED),
                this.createNotification(NOTIFICATION_IDS.get(2), mostFailedTemplate, NotificationStatus.PENDING),
                this.createNotification(NOTIFICATION_IDS.get(3), secondTemplate, NotificationStatus.FAILED),
                this.createNotification(NOTIFICATION_IDS.get(4), secondTemplate, NotificationStatus.DELIVERED),
                this.createNotification(NOTIFICATION_IDS.get(5), noFailureTemplate, NotificationStatus.PENDING))
                .stream()
                .filter(notification -> !this.notificationRepository.existsById(notification.getId()))
                .toList();
        this.notificationRepository.saveAll(notifications);
        log.info("Notification failure report notifications seeded: {}", notifications.size());
    }

    private NotificationTemplateEntity createTemplate(UUID id, String eventType, Channel channel) {
        return NotificationTemplateEntity.builder()
                .id(id)
                .eventType(eventType)
                .subjectTemplate("Delivery report demonstration")
                .bodyTemplate("Demonstration notification for delivery failure reporting.")
                .channel(channel)
                .build();
    }

    private NotificationEntity createNotification(
            UUID id, NotificationTemplateEntity template, NotificationStatus status) {
        return NotificationEntity.builder()
                .id(id)
                .title("Delivery report demonstration")
                .message("Demonstration notification for delivery failure reporting.")
                .createdAt(LocalDate.now())
                .notificationTemplate(template)
                .priority(Priority.MEDIUM)
                .notificationStatus(status)
                .recipientId(id)
                .build();
    }
}
