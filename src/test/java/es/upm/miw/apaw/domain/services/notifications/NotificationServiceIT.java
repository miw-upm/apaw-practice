package es.upm.miw.apaw.domain.services.notifications;

import es.upm.miw.apaw.adapters.out.notifications.postgres.NotificationEntity;
import es.upm.miw.apaw.adapters.out.notifications.postgres.NotificationRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.notifications.Channel;
import es.upm.miw.apaw.domain.model.notifications.CreationNotification;
import es.upm.miw.apaw.domain.model.notifications.Notification;
import es.upm.miw.apaw.domain.model.notifications.NotificationFindCriteria;
import es.upm.miw.apaw.domain.model.notifications.NotificationStatus;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplate;
import es.upm.miw.apaw.domain.model.notifications.Priority;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static es.upm.miw.apaw.config.seeders.NotificationTemplateFailureReportSeederForDev.MOST_FAILED_EVENT_TYPE;
import static es.upm.miw.apaw.config.seeders.NotificationTemplateFailureReportSeederForDev.SECOND_EVENT_TYPE;
import static es.upm.miw.apaw.config.seeders.NotificationTemplateFailureReportSeederForDev.USER_IDS;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class NotificationServiceIT {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private NotificationTemplateService notificationTemplateService;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private Validator validator;
    @MockitoBean
    private UserFinder userFinder;

    @Test
    @Transactional
    void testCreate() {
        NotificationTemplate template = this.createTemplate();
        UserSnapshot user = UserSnapshot.builder()
                .id(UUID.randomUUID())
                .mobile("600000100")
                .firstName("recipient")
                .build();
        when(this.userFinder.read(user.getId())).thenReturn(user);
        CreationNotification creation = CreationNotification.builder()
                .title("Notification title")
                .message("Notification message")
                .notificationTemplateId(template.getId())
                .userId(user.getId())
                .build();

        Notification notification = this.notificationService.create(creation);

        assertThat(notification.getId()).isNotNull();
        assertThat(notification.getCreatedAt()).isEqualTo(LocalDate.now());
        assertThat(notification.getSentAt()).isNull();
        assertThat(notification.getPriority()).isEqualTo(Priority.MEDIUM);
        assertThat(notification.getNotificationStatus()).isEqualTo(NotificationStatus.PENDING);
        assertThat(notification.getNotificationTemplate().getId()).isEqualTo(template.getId());
        assertThat(notification.getRecipient()).isEqualTo(user);
        NotificationEntity stored = this.notificationRepository.findById(notification.getId()).orElseThrow();
        assertThat(stored.getTitle()).isEqualTo(creation.getTitle());
        assertThat(stored.getMessage()).isEqualTo(creation.getMessage());
        assertThat(stored.getNotificationTemplate().getId()).isEqualTo(template.getId());
        assertThat(stored.getRecipientId()).isEqualTo(user.getId());
        verify(this.userFinder, times(1)).read(user.getId());
    }

    @Test
    @Transactional
    void testCreateUserNotFound() {
        NotificationTemplate template = this.createTemplate();
        UUID userId = UUID.randomUUID();
        when(this.userFinder.read(userId))
                .thenThrow(new NotFoundException("Not found on read user by id " + userId));

        assertThatThrownBy(() -> this.notificationService.create(this.creation(template.getId(), userId)))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(userId.toString());
        verify(this.userFinder, times(1)).read(userId);
    }

    @Test
    void testCreateNotificationTemplateNotFound() {
        UUID templateId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        assertThatThrownBy(() -> this.notificationService.create(this.creation(templateId, userId)))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(templateId.toString());
        verifyNoInteractions(this.userFinder);
    }

    @Test
    void testCreationInputRequiresContentAndReferences() {
        CreationNotification invalidCreation = CreationNotification.builder()
                .title(" ")
                .message(null)
                .build();

        Set<String> invalidProperties = this.validator.validate(invalidCreation).stream()
                .map(violation -> violation.getPropertyPath().toString())
                .collect(Collectors.toSet());

        assertThat(invalidProperties).containsExactlyInAnyOrder(
                "title", "message", "notificationTemplateId", "userId");
    }

    @Test
    void testCreationInputRejectsContentOverColumnLength() {
        CreationNotification invalidCreation = CreationNotification.builder()
                .title("t".repeat(256))
                .message("m".repeat(256))
                .notificationTemplateId(UUID.randomUUID())
                .userId(UUID.randomUUID())
                .build();

        Set<String> invalidProperties = this.validator.validate(invalidCreation).stream()
                .map(violation -> violation.getPropertyPath().toString())
                .collect(Collectors.toSet());

        assertThat(invalidProperties).containsExactlyInAnyOrder("title", "message");
    }

    @Test
    void testFindByPriorityAndRelatedTemplateEventType() {
        UUID expectedUserId = USER_IDS.getFirst();
        this.stubUsers(expectedUserId);

        List<Notification> notifications = this.notificationService.find(NotificationFindCriteria.builder()
                .priority(Priority.HIGH)
                .eventType(MOST_FAILED_EVENT_TYPE)
                .build());

        assertThat(notifications).hasSize(1);
        assertThat(notifications.getFirst().getRecipient().getId()).isEqualTo(expectedUserId);
        assertThat(notifications.getFirst().getPriority()).isEqualTo(Priority.HIGH);
        assertThat(notifications.getFirst().getNotificationTemplate().getEventType())
                .isEqualTo(MOST_FAILED_EVENT_TYPE);
        verify(this.userFinder, times(1)).findByIds(Set.of(expectedUserId));
    }

    @Test
    void testNullOptionalCriteriaDoNotRestrictPrioritySearch() {
        Set<UUID> expectedUserIds = Set.of(USER_IDS.get(0), USER_IDS.get(4));
        when(this.userFinder.findByIds(expectedUserIds))
                .thenReturn(expectedUserIds.stream().map(this::seededUser).toList());

        List<Notification> notifications = this.notificationService.find(NotificationFindCriteria.builder()
                .priority(Priority.HIGH)
                .build());

        assertThat(notifications).extracting(notification -> notification.getRecipient().getId())
                .containsExactlyInAnyOrderElementsOf(expectedUserIds);
        verify(this.userFinder, times(1)).findByIds(expectedUserIds);
    }

    @Test
    void testFindSentNotifications() {
        UUID expectedUserId = USER_IDS.get(4);
        this.stubUsers(expectedUserId);

        List<Notification> notifications = this.notificationService.find(NotificationFindCriteria.builder()
                .eventType(SECOND_EVENT_TYPE)
                .sent(true)
                .build());

        assertThat(notifications).hasSize(1);
        assertThat(notifications.getFirst().getRecipient().getId()).isEqualTo(expectedUserId);
        assertThat(notifications.getFirst().getSentAt()).isNotNull();
        assertThat(notifications.getFirst().getNotificationStatus()).isEqualTo(NotificationStatus.DELIVERED);
        verify(this.userFinder, times(1)).findByIds(Set.of(expectedUserId));
    }

    @Test
    void testFindUnsentNotifications() {
        UUID expectedUserId = USER_IDS.get(3);
        this.stubUsers(expectedUserId);

        List<Notification> notifications = this.notificationService.find(NotificationFindCriteria.builder()
                .eventType(SECOND_EVENT_TYPE)
                .sent(false)
                .build());

        assertThat(notifications).hasSize(1);
        assertThat(notifications.getFirst().getRecipient().getId()).isEqualTo(expectedUserId);
        assertThat(notifications.getFirst().getSentAt()).isNull();
        assertThat(notifications.getFirst().getNotificationStatus()).isEqualTo(NotificationStatus.FAILED);
        verify(this.userFinder, times(1)).findByIds(Set.of(expectedUserId));
    }

    @Test
    void testFindByRecipientEmailUsingOneBulkUserLookup() {
        Set<UUID> candidateUserIds = Set.of(USER_IDS.get(0), USER_IDS.get(1), USER_IDS.get(2));
        List<UserSnapshot> users = candidateUserIds.stream()
                .map(this::seededUser)
                .toList();
        when(this.userFinder.findByIds(candidateUserIds)).thenReturn(users);

        List<Notification> notifications = this.notificationService.find(NotificationFindCriteria.builder()
                .eventType(MOST_FAILED_EVENT_TYPE)
                .recipientEmail("cliente1@example.com")
                .build());

        assertThat(notifications).hasSize(1);
        assertThat(notifications.getFirst().getRecipient().getId()).isEqualTo(USER_IDS.get(1));
        assertThat(notifications.getFirst().getRecipient().getEmail()).isEqualTo("cliente1@example.com");
        verify(this.userFinder, times(1)).findByIds(candidateUserIds);
        verifyNoMoreInteractions(this.userFinder);
    }

    @Test
    void testFindWithoutDatabaseMatchesDoesNotCallUserService() {
        List<Notification> notifications = this.notificationService.find(NotificationFindCriteria.builder()
                .eventType("EVENT_TYPE_WITHOUT_NOTIFICATIONS")
                .build());

        assertThat(notifications).isEmpty();
        verifyNoInteractions(this.userFinder);
    }

    private NotificationTemplate createTemplate() {
        return this.notificationTemplateService.create(NotificationTemplate.builder()
                .eventType("IT_NOTIFICATION_" + UUID.randomUUID())
                .subjectTemplate("IT notification subject")
                .bodyTemplate("IT notification body")
                .channel(Channel.EMAIL)
                .build());
    }

    private CreationNotification creation(UUID templateId, UUID userId) {
        return CreationNotification.builder()
                .title("Notification title")
                .message("Notification message")
                .notificationTemplateId(templateId)
                .priority(Priority.HIGH)
                .userId(userId)
                .build();
    }

    private void stubUsers(UUID... userIds) {
        Set<UUID> ids = Set.of(userIds);
        when(this.userFinder.findByIds(ids)).thenReturn(ids.stream().map(this::seededUser).toList());
    }

    private UserSnapshot seededUser(UUID id) {
        int userIndex = USER_IDS.indexOf(id);
        return UserSnapshot.builder()
                .id(id)
                .email("cliente" + userIndex + "@example.com")
                .build();
    }
}
