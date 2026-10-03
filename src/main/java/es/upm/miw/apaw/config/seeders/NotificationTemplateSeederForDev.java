package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.notifications.postgres.NotificationTemplateEntity;
import es.upm.miw.apaw.adapters.out.notifications.postgres.NotificationTemplateRepository;
import es.upm.miw.apaw.domain.model.notifications.Channel;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(4)
@RequiredArgsConstructor
public class NotificationTemplateSeederForDev implements ApplicationRunner {
    private static final String PREFIX = "ffffffff-aaaa-bbbb-cccc-ddddeeee";
    private static final String MAX_EVENT_TYPE = "e".repeat(60);
    private static final String MAX_SUBJECT_TEMPLATE = "s".repeat(60);
    private static final String MAX_BODY_TEMPLATE = "b".repeat(500);

    public static final UUID ID_0 = UUID.fromString(PREFIX + "0000");
    public static final NotificationTemplate TEMPLATE_0 = NotificationTemplate.builder()
            .id(ID_0)
            .eventType("USER_REGISTERED")
            .subjectTemplate("Welcome, {{firstName}}")
            .bodyTemplate("Hello {{firstName}}, your account has been created.")
            .channel(Channel.EMAIL)
            .build();
    public static final UUID ID_1 = UUID.fromString(PREFIX + "0001");
    public static final NotificationTemplate TEMPLATE_1 = NotificationTemplate.builder()
            .id(ID_1)
            .eventType("APPOINTMENT_REMINDER")
            .subjectTemplate("Appointment reminder")
            .bodyTemplate("Your appointment is scheduled for {{date}}.")
            .channel(Channel.SMS)
            .build();
    public static final UUID ID_2 = UUID.fromString(PREFIX + "0002");
    public static final NotificationTemplate TEMPLATE_2 = NotificationTemplate.builder()
            .id(ID_2)
            .eventType("DOCUMENT_STATUS_CHANGED")
            .subjectTemplate("Document status changed")
            .bodyTemplate("The status of document {{documentId}} is now {{status}}.")
            .channel(Channel.PUSH)
            .build();
    public static final UUID ID_3 = UUID.fromString(PREFIX + "0003");
    public static final NotificationTemplate TEMPLATE_3 = NotificationTemplate.builder()
            .id(ID_3)
            .eventType(MAX_EVENT_TYPE)
            .subjectTemplate(MAX_SUBJECT_TEMPLATE)
            .bodyTemplate(MAX_BODY_TEMPLATE)
            .channel(Channel.EMAIL)
            .build();

    private final NotificationTemplateRepository notificationTemplateRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load Notification Templates -----------");
        this.seedNotificationTemplates();
    }

    private void seedNotificationTemplates() {
        List<NotificationTemplateEntity> templates = List.of(TEMPLATE_0, TEMPLATE_1, TEMPLATE_2, TEMPLATE_3).stream()
                .filter(template -> !this.notificationTemplateRepository.existsById(template.getId()))
                .map(NotificationTemplateEntity::new)
                .toList();
        this.notificationTemplateRepository.saveAll(templates);
        log.warn("        ------- notification templates: {} added", templates.size());
    }
}
