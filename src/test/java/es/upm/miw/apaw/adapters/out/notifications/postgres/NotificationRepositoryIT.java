package es.upm.miw.apaw.adapters.out.notifications.postgres;

import es.upm.miw.apaw.domain.model.notifications.Channel;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplateFailureReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static es.upm.miw.apaw.config.seeders.NotificationTemplateFailureReportSeederForDev.EVENT_TYPE_PREFIX;
import static es.upm.miw.apaw.config.seeders.NotificationTemplateFailureReportSeederForDev.MOST_FAILED_EVENT_TYPE;
import static es.upm.miw.apaw.config.seeders.NotificationTemplateFailureReportSeederForDev.NO_FAILURE_EVENT_TYPE;
import static es.upm.miw.apaw.config.seeders.NotificationTemplateFailureReportSeederForDev.SECOND_EVENT_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;

@SpringBootTest
@ActiveProfiles("test")
class NotificationRepositoryIT {
    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void testFindTemplateFailureReport() {
        List<NotificationTemplateFailureReport> report = this.notificationRepository
                .findTemplateFailureReport().stream()
                .filter(item -> item.getEventType().startsWith(EVENT_TYPE_PREFIX))
                .toList();

        assertThat(report).isSortedAccordingTo(Comparator
                .comparingDouble(NotificationTemplateFailureReport::getFailureRate).reversed());
        assertThat(report).extracting(NotificationTemplateFailureReport::getEventType)
                .containsExactly(
                        MOST_FAILED_EVENT_TYPE,
                        SECOND_EVENT_TYPE,
                        NO_FAILURE_EVENT_TYPE);
        Map<String, NotificationTemplateFailureReport> reportByEventType = report.stream()
                .collect(Collectors.toMap(NotificationTemplateFailureReport::getEventType, item -> item));
        assertThat(reportByEventType.get(MOST_FAILED_EVENT_TYPE))
                .satisfies(item -> {
                    assertThat(item.getChannel()).isEqualTo(Channel.EMAIL);
                    assertThat(item.getTotalNotificationCount()).isEqualTo(3);
                    assertThat(item.getFailedNotificationCount()).isEqualTo(2);
                    assertThat(item.getFailureRate()).isCloseTo(200.0 / 3,
                            offset(0.001));
                });
        assertThat(reportByEventType.get(SECOND_EVENT_TYPE))
                .satisfies(item -> {
                    assertThat(item.getChannel()).isEqualTo(Channel.SMS);
                    assertThat(item.getTotalNotificationCount()).isEqualTo(2);
                    assertThat(item.getFailedNotificationCount()).isEqualTo(1);
                    assertThat(item.getFailureRate()).isEqualTo(50.0);
                });
        assertThat(reportByEventType.get(NO_FAILURE_EVENT_TYPE))
                .satisfies(item -> {
                    assertThat(item.getChannel()).isEqualTo(Channel.PUSH);
                    assertThat(item.getTotalNotificationCount()).isEqualTo(1);
                    assertThat(item.getFailedNotificationCount()).isZero();
                    assertThat(item.getFailureRate()).isZero();
                });
    }
}
