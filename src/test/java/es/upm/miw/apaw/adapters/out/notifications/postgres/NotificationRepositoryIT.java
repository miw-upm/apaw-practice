package es.upm.miw.apaw.adapters.out.notifications.postgres;

import es.upm.miw.apaw.domain.model.notifications.Channel;
import es.upm.miw.apaw.domain.model.notifications.NotificationStatus;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplateFailureReport;
import es.upm.miw.apaw.domain.model.notifications.Priority;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;

@SpringBootTest
@ActiveProfiles("test")
class NotificationRepositoryIT {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationTemplateRepository notificationTemplateRepository;

    @Test
    @Transactional
    void testFindTemplateFailureReport() {
        String reportPrefix = "IT_FAIL_" + UUID.randomUUID();
        NotificationTemplateEntity mostFailedTemplate = this.createTemplate(
                reportPrefix + "_MOST_FAILED", Channel.EMAIL);
        NotificationTemplateEntity secondMostFailedTemplate = this.createTemplate(
                reportPrefix + "_SECOND", Channel.SMS);
        NotificationTemplateEntity noFailureTemplate = this.createTemplate(
                reportPrefix + "_NONE", Channel.PUSH);
        this.notificationTemplateRepository.saveAllAndFlush(
                List.of(mostFailedTemplate, secondMostFailedTemplate, noFailureTemplate));

        this.createNotifications(mostFailedTemplate, NotificationStatus.FAILED, 2);
        this.createNotifications(mostFailedTemplate, NotificationStatus.PENDING, 1);
        this.createNotifications(secondMostFailedTemplate, NotificationStatus.FAILED, 1);
        this.createNotifications(secondMostFailedTemplate, NotificationStatus.DELIVERED, 1);
        this.createNotifications(noFailureTemplate, NotificationStatus.PENDING, 1);
        this.notificationRepository.flush();

        List<NotificationTemplateFailureReport> report = this.notificationRepository
                .findTemplateFailureReport().stream()
                .filter(item -> item.getEventType().startsWith(reportPrefix))
                .toList();

        assertThat(report).isSortedAccordingTo(Comparator
                .comparingDouble(NotificationTemplateFailureReport::getFailureRate).reversed());
        assertThat(report).extracting(NotificationTemplateFailureReport::getEventType)
                .containsExactly(
                        mostFailedTemplate.getEventType(),
                        secondMostFailedTemplate.getEventType(),
                        noFailureTemplate.getEventType());
        Map<String, NotificationTemplateFailureReport> reportByEventType = report.stream()
                .collect(Collectors.toMap(NotificationTemplateFailureReport::getEventType, item -> item));
        assertThat(reportByEventType.get(mostFailedTemplate.getEventType()))
                .satisfies(item -> {
                    assertThat(item.getChannel()).isEqualTo(Channel.EMAIL);
                    assertThat(item.getTotalNotificationCount()).isEqualTo(3);
                    assertThat(item.getFailedNotificationCount()).isEqualTo(2);
                    assertThat(item.getFailureRate()).isCloseTo(200.0 / 3,
                            offset(0.001));
                });
        assertThat(reportByEventType.get(secondMostFailedTemplate.getEventType()))
                .satisfies(item -> {
                    assertThat(item.getChannel()).isEqualTo(Channel.SMS);
                    assertThat(item.getTotalNotificationCount()).isEqualTo(2);
                    assertThat(item.getFailedNotificationCount()).isEqualTo(1);
                    assertThat(item.getFailureRate()).isEqualTo(50.0);
                });
        assertThat(reportByEventType.get(noFailureTemplate.getEventType()))
                .satisfies(item -> {
                    assertThat(item.getChannel()).isEqualTo(Channel.PUSH);
                    assertThat(item.getTotalNotificationCount()).isEqualTo(1);
                    assertThat(item.getFailedNotificationCount()).isZero();
                    assertThat(item.getFailureRate()).isZero();
                });
    }

    private NotificationTemplateEntity createTemplate(String eventType, Channel channel) {
        return NotificationTemplateEntity.builder()
                .id(UUID.randomUUID())
                .eventType(eventType)
                .subjectTemplate("Integration test subject")
                .bodyTemplate("Integration test body")
                .channel(channel)
                .build();
    }

    private void createNotifications(
            NotificationTemplateEntity template, NotificationStatus status, int count) {
        List<NotificationEntity> notifications = java.util.stream.IntStream.range(0, count)
                .mapToObj(index -> NotificationEntity.builder()
                        .id(UUID.randomUUID())
                        .title("Integration test notification")
                        .message("Integration test message")
                        .createdAt(LocalDate.now())
                        .notificationTemplate(template)
                        .priority(Priority.MEDIUM)
                        .notificationStatus(status)
                        .recipientId(UUID.randomUUID())
                        .build())
                .toList();
        this.notificationRepository.saveAll(notifications);
    }
}
