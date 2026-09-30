package es.upm.miw.apaw.domain.services.notifications;

import es.upm.miw.apaw.adapters.out.notifications.postgres.NotificationEntity;
import es.upm.miw.apaw.adapters.out.notifications.postgres.NotificationRepository;
import es.upm.miw.apaw.adapters.out.notifications.postgres.NotificationTemplateRepository;
import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.notifications.Channel;
import es.upm.miw.apaw.domain.model.notifications.NotificationStatus;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplate;
import es.upm.miw.apaw.domain.model.notifications.Priority;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.NotificationTemplateSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class NotificationTemplateServiceIT {
    @Autowired
    private NotificationTemplateService notificationTemplateService;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationTemplateRepository notificationTemplateRepository;

    @Test
    void testReadSeeder() {
        assertThat(this.notificationTemplateService.read(ID_0))
                .usingRecursiveComparison().isEqualTo(TEMPLATE_0);
    }

    @Test
    void testReadSeederAtMaximumFieldLengths() {
        NotificationTemplate template = this.notificationTemplateService.read(ID_3);
        assertThat(template.getEventType()).hasSize(60);
        assertThat(template.getSubjectTemplate()).hasSize(60);
        assertThat(template.getBodyTemplate()).hasSize(500);
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.notificationTemplateService.read(id))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testFindAllIncludesSeederAndHasDeterministicOrder() {
        List<NotificationTemplate> templates = this.notificationTemplateService.findAll();
        List<NotificationTemplate> templatesAgain = this.notificationTemplateService.findAll();

        assertThat(templates).extracting(NotificationTemplate::getId).contains(ID_0);
        assertThat(templatesAgain).extracting(NotificationTemplate::getId)
                .containsExactlyElementsOf(templates.stream().map(NotificationTemplate::getId).toList());
    }

    @Test
    void testCreateAndDefaultChannel() {
        NotificationTemplate template = this.createTemplate();
        NotificationTemplate stored = this.notificationTemplateService.read(template.getId());
        assertThat(stored).usingRecursiveComparison().isEqualTo(template);
        assertThat(stored.getChannel()).isEqualTo(Channel.EMAIL);
    }

    @Test
    void testCreateDuplicateEventType() {
        NotificationTemplate template = NotificationTemplate.builder()
                .eventType(TEMPLATE_0.getEventType())
                .subjectTemplate("Another subject")
                .bodyTemplate("Another body")
                .channel(Channel.EMAIL)
                .build();
        assertThatThrownBy(() -> this.notificationTemplateService.create(template))
                .isInstanceOf(ConflictException.class).hasMessageContaining(TEMPLATE_0.getEventType());
    }

    @Test
    void testCreateRejectsEventTypeOverLimit() {
        NotificationTemplate template = NotificationTemplate.builder()
                .eventType("e".repeat(61))
                .subjectTemplate("Subject")
                .bodyTemplate("Body")
                .channel(Channel.EMAIL)
                .build();
        assertThatThrownBy(() -> this.notificationTemplateService.create(template))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    void testUpdateReplacesFieldsAndKeepsPathId() {
        NotificationTemplate original = this.createTemplate();
        NotificationTemplate replacement = NotificationTemplate.builder()
                .id(UUID.randomUUID())
                .eventType("UPDATED_" + UUID.randomUUID())
                .subjectTemplate("Replacement subject")
                .bodyTemplate("Replacement body")
                .channel(Channel.PUSH)
                .build();

        NotificationTemplate updated = this.notificationTemplateService.update(original.getId(), replacement);

        assertThat(updated.getId()).isEqualTo(original.getId());
        assertThat(updated.getEventType()).isEqualTo(replacement.getEventType());
        assertThat(updated.getSubjectTemplate()).isEqualTo(replacement.getSubjectTemplate());
        assertThat(updated.getBodyTemplate()).isEqualTo(replacement.getBodyTemplate());
        assertThat(updated.getChannel()).isEqualTo(replacement.getChannel());
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.notificationTemplateService.update(id, TEMPLATE_0))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testUpdateDuplicateEventTypeLeavesTemplateUnchanged() {
        NotificationTemplate template = this.createTemplate();
        NotificationTemplate replacement = NotificationTemplate.builder()
                .eventType(TEMPLATE_0.getEventType())
                .subjectTemplate("Updated subject")
                .bodyTemplate("Updated body")
                .channel(Channel.EMAIL)
                .build();

        assertThatThrownBy(() -> this.notificationTemplateService.update(template.getId(), replacement))
                .isInstanceOf(ConflictException.class).hasMessageContaining(TEMPLATE_0.getEventType());
        assertThat(this.notificationTemplateService.read(template.getId()).getEventType())
                .isEqualTo(template.getEventType());
    }

    @Test
    void testPatchUpdatesOnlyProvidedFields() {
        NotificationTemplate template = this.createTemplate();
        NotificationTemplate patch = NotificationTemplate.builder()
                .subjectTemplate("Patched subject")
                .build();

        NotificationTemplate updated = this.notificationTemplateService.patch(template.getId(), patch);

        assertThat(updated.getId()).isEqualTo(template.getId());
        assertThat(updated.getSubjectTemplate()).isEqualTo("Patched subject");
        assertThat(updated.getEventType()).isEqualTo(template.getEventType());
        assertThat(updated.getBodyTemplate()).isEqualTo(template.getBodyTemplate());
        assertThat(updated.getChannel()).isEqualTo(template.getChannel());
    }

    @Test
    void testPatchRejectsEventTypeOverLimit() {
        NotificationTemplate template = this.createTemplate();
        NotificationTemplate patch = NotificationTemplate.builder().eventType("e".repeat(61)).build();

        assertThatThrownBy(() -> this.notificationTemplateService.patch(template.getId(), patch))
                .isInstanceOf(BadRequestException.class);
        assertThat(this.notificationTemplateService.read(template.getId()).getEventType())
                .isEqualTo(template.getEventType());
    }

    @Test
    void testDelete() {
        NotificationTemplate template = this.createTemplate();
        this.notificationTemplateService.delete(template.getId());
        assertThatThrownBy(() -> this.notificationTemplateService.read(template.getId()))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteReferencedTemplate() {
        NotificationTemplate template = this.createTemplate();
        NotificationEntity notification = NotificationEntity.builder()
                .id(UUID.randomUUID())
                .title("Notification referencing template")
                .message("A notification message")
                .createdAt(LocalDate.now())
                .notificationTemplate(this.notificationTemplateRepository.getReferenceById(template.getId()))
                .priority(Priority.MEDIUM)
                .notificationStatus(NotificationStatus.PENDING)
                .recipientId(UUID.randomUUID())
                .build();
        this.notificationRepository.saveAndFlush(notification);

        assertThatThrownBy(() -> this.notificationTemplateService.delete(template.getId()))
                .isInstanceOf(ConflictException.class).hasMessageContaining(template.getId().toString());
        assertThat(this.notificationTemplateService.read(template.getId()).getId()).isEqualTo(template.getId());
    }

    private NotificationTemplate createTemplate() {
        return this.notificationTemplateService.create(NotificationTemplate.builder()
                .eventType("IT_" + UUID.randomUUID())
                .subjectTemplate("IT subject")
                .bodyTemplate("IT notification body")
                .build());
    }
}
