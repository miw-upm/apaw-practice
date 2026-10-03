package es.upm.miw.apaw.functionaltests.notifications;

import es.upm.miw.apaw.adapters.in.notifications.NotificationTemplateResource;
import es.upm.miw.apaw.domain.model.notifications.Channel;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.NotificationTemplateSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class NotificationTemplateResourceFT {
    @LocalServerPort
    private int port;
    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port).build();
    }

    @Test
    void testReadSeeder() {
        this.restTestClient.get().uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE + "/" + ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(NotificationTemplate.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(TEMPLATE_0));
    }

    @Test
    void testReadSeederAtMaximumFieldLengths() {
        this.restTestClient.get().uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE + "/" + ID_3)
                .exchange()
                .expectStatus().isOk()
                .expectBody(NotificationTemplate.class)
                .value(body -> {
                    assertThat(body.getEventType()).hasSize(60);
                    assertThat(body.getSubjectTemplate()).hasSize(60);
                    assertThat(body.getBodyTemplate()).hasSize(500);
                });
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        this.restTestClient.get().uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(id.toString()));
    }

    @Test
    void testFindAllContainsSeederAndHasDeterministicOrder() {
        List<NotificationTemplate> templates = this.findAll();
        List<NotificationTemplate> templatesAgain = this.findAll();

        assertThat(templates).extracting(NotificationTemplate::getId).contains(ID_0);
        assertThat(templatesAgain).extracting(NotificationTemplate::getId)
                .containsExactlyElementsOf(templates.stream().map(NotificationTemplate::getId).toList());
    }

    @Test
    void testCreate() {
        NotificationTemplate request = this.template("FT_" + UUID.randomUUID());
        request.setChannel(null);
        this.restTestClient.post().uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE)
                .body(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(NotificationTemplate.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getEventType()).isEqualTo(request.getEventType());
                    assertThat(body.getChannel()).isEqualTo(Channel.EMAIL);
                });
    }

    @Test
    void testCreateRejectsOversizedFields() {
        NotificationTemplate eventTypeTooLong = this.template("e".repeat(61));
        this.restTestClient.post().uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE)
                .body(eventTypeTooLong)
                .exchange()
                .expectStatus().isBadRequest();

        NotificationTemplate subjectTooLong = this.template("FT_" + UUID.randomUUID());
        subjectTooLong.setSubjectTemplate("s".repeat(61));
        this.restTestClient.post().uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE)
                .body(subjectTooLong)
                .exchange()
                .expectStatus().isBadRequest();

        NotificationTemplate bodyTooLong = this.template("FT_" + UUID.randomUUID());
        bodyTooLong.setBodyTemplate("b".repeat(501));
        this.restTestClient.post().uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE)
                .body(bodyTooLong)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateDuplicateEventType() {
        this.restTestClient.post().uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE)
                .body(this.template(TEMPLATE_0.getEventType()))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdateReplacesAllFields() {
        NotificationTemplate original = this.createTemplate();
        NotificationTemplate replacement = NotificationTemplate.builder()
                .id(UUID.randomUUID())
                .eventType("FT_UPDATED_" + UUID.randomUUID())
                .subjectTemplate("Replacement subject")
                .bodyTemplate("Replacement body")
                .channel(Channel.PUSH)
                .build();

        this.restTestClient.put().uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE + "/" + original.getId())
                .body(replacement)
                .exchange()
                .expectStatus().isOk()
                .expectBody(NotificationTemplate.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(original.getId());
                    assertThat(body.getEventType()).isEqualTo(replacement.getEventType());
                    assertThat(body.getSubjectTemplate()).isEqualTo(replacement.getSubjectTemplate());
                    assertThat(body.getBodyTemplate()).isEqualTo(replacement.getBodyTemplate());
                    assertThat(body.getChannel()).isEqualTo(replacement.getChannel());
                });
    }

    @Test
    void testUpdateRequiresCompleteTemplate() {
        NotificationTemplate request = NotificationTemplate.builder()
                .eventType("FT_INCOMPLETE_" + UUID.randomUUID())
                .build();
        this.restTestClient.put()
                .uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE + "/" + ID_0)
                .body(request)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testUpdateNotFound() {
        this.restTestClient.put()
                .uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE + "/" + UUID.randomUUID())
                .body(this.template("FT_MISSING_" + UUID.randomUUID()))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateDuplicateEventType() {
        NotificationTemplate template = this.createTemplate();
        this.restTestClient.put()
                .uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE + "/" + template.getId())
                .body(this.template(TEMPLATE_0.getEventType()))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testPatchUpdatesOnlyProvidedFields() {
        NotificationTemplate original = this.createTemplate();
        String updatedSubject = "Patched subject";
        this.restTestClient.patch()
                .uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE + "/" + original.getId())
                .body(Map.of("subjectTemplate", updatedSubject))
                .exchange()
                .expectStatus().isOk()
                .expectBody(NotificationTemplate.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(original.getId());
                    assertThat(body.getSubjectTemplate()).isEqualTo(updatedSubject);
                    assertThat(body.getEventType()).isEqualTo(original.getEventType());
                    assertThat(body.getBodyTemplate()).isEqualTo(original.getBodyTemplate());
                    assertThat(body.getChannel()).isEqualTo(original.getChannel());
                });
    }

    @Test
    void testPatchRejectsOversizedFields() {
        this.restTestClient.patch()
                .uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE + "/" + ID_0)
                .body(Map.of("bodyTemplate", "b".repeat(501)))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatchNotFound() {
        this.restTestClient.patch()
                .uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE + "/" + UUID.randomUUID())
                .body(Map.of("subjectTemplate", "Updated subject"))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDelete() {
        NotificationTemplate template = this.createTemplate();
        this.restTestClient.delete()
                .uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE + "/" + template.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();
        this.restTestClient.get()
                .uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE + "/" + template.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteNotFound() {
        this.restTestClient.delete()
                .uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE + "/" + UUID.randomUUID())
                .exchange()
                .expectStatus().isNoContent();
    }

    private NotificationTemplate createTemplate() {
        return this.restTestClient.post().uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE)
                .body(this.template("FT_" + UUID.randomUUID()))
                .exchange().expectStatus().isCreated()
                .expectBody(NotificationTemplate.class).returnResult().getResponseBody();
    }

    private List<NotificationTemplate> findAll() {
        NotificationTemplate[] templates = this.restTestClient.get()
                .uri(NotificationTemplateResource.NOTIFICATION_TEMPLATE)
                .exchange().expectStatus().isOk()
                .expectBody(NotificationTemplate[].class)
                .returnResult().getResponseBody();
        return templates == null ? List.of() : Arrays.asList(templates);
    }

    private NotificationTemplate template(String eventType) {
        return NotificationTemplate.builder()
                .eventType(eventType)
                .subjectTemplate("FT notification subject")
                .bodyTemplate("FT notification body")
                .channel(Channel.EMAIL)
                .build();
    }
}
