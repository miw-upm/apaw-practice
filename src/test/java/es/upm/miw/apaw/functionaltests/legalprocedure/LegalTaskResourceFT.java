package es.upm.miw.apaw.functionaltests.legalprocedure;

import es.upm.miw.apaw.adapters.in.legalprocedure.LegalTaskResource;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalTask;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalTaskStatusUpdate;
import es.upm.miw.apaw.domain.models.legalprocedure.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.LegalProcedureSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class LegalTaskResourceFT {
    @LocalServerPort
    private int port;
    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port).build();
    }

    @Test
    void testRead() {
        this.restTestClient.get().uri(LegalTaskResource.LEGAL_TASKS + "/" + ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalTask.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(TASK_0));
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        this.restTestClient.get().uri(LegalTaskResource.LEGAL_TASKS + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(id.toString()));
    }

    @Test
    void testFindAll() {
        this.restTestClient.get().uri(LegalTaskResource.LEGAL_TASKS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalTask[].class)
                .value(body -> assertThat(body).extracting(LegalTask::getId)
                        .containsSubsequence(ID_1, ID_2, ID_4, ID_0, ID_5, ID_3));
    }

    @Test
    void testCreateDefaultsToCurrent() {
        this.restTestClient.post().uri(LegalTaskResource.LEGAL_TASKS)
                .body(LegalTask.builder().title("Task " + UUID.randomUUID()).build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(LegalTask.class)
                .value(body -> assertThat(body).isNotNull().extracting(LegalTask::getTaskStatus)
                        .isEqualTo(TaskStatus.CURRENT));
    }

    @Test
    void testCreateBlankTitle() {
        this.restTestClient.post().uri(LegalTaskResource.LEGAL_TASKS)
                .body(LegalTask.builder().title(" ").build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateDuplicateTitle() {
        this.restTestClient.post().uri(LegalTaskResource.LEGAL_TASKS)
                .body(LegalTask.builder().title(TASK_0.getTitle()).build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdate() {
        LegalTask task = this.createTask();
        this.restTestClient.put().uri(LegalTaskResource.LEGAL_TASKS + "/" + task.getId())
                .body(LegalTask.builder().title(task.getTitle()).taskStatus(TaskStatus.WITHDRAWN).build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalTask.class).value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(task.getId());
                    assertThat(body.getTitle()).isEqualTo(task.getTitle());
                    assertThat(body.getTaskStatus()).isEqualTo(TaskStatus.WITHDRAWN);
                });
    }

    @Test
    void testUpdateNotFound() {
        this.restTestClient.put().uri(LegalTaskResource.LEGAL_TASKS + "/" + UUID.randomUUID())
                .body(LegalTask.builder().title("Missing task").build())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateDuplicateTitle() {
        LegalTask task = this.createTask();
        this.restTestClient.put().uri(LegalTaskResource.LEGAL_TASKS + "/" + task.getId())
                .body(LegalTask.builder().title(TASK_0.getTitle()).build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testDelete() {
        LegalTask task = this.createTask();
        this.restTestClient.delete().uri(LegalTaskResource.LEGAL_TASKS + "/" + task.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();
        this.restTestClient.get().uri(LegalTaskResource.LEGAL_TASKS + "/" + task.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatch() {
        LegalTask first = this.createTask();
        LegalTask second = this.createTask();
        this.restTestClient.patch().uri(LegalTaskResource.LEGAL_TASKS)
                .body(List.of(new LegalTaskStatusUpdate(first.getId(), TaskStatus.WITHDRAWN),
                        new LegalTaskStatusUpdate(second.getId(), TaskStatus.DEPRECATED)))
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
        this.assertStatus(first.getId(), TaskStatus.WITHDRAWN);
        this.assertStatus(second.getId(), TaskStatus.DEPRECATED);
    }

    @Test
    void testPatchNotFoundChangesNothing() {
        LegalTask task = this.createTask();
        UUID missingId = UUID.randomUUID();
        this.restTestClient.patch().uri(LegalTaskResource.LEGAL_TASKS)
                .body(List.of(new LegalTaskStatusUpdate(task.getId(), TaskStatus.WITHDRAWN),
                        new LegalTaskStatusUpdate(missingId, TaskStatus.DEPRECATED)))
                .exchange()
                .expectStatus().isNotFound();
        this.assertStatus(task.getId(), TaskStatus.CURRENT);
    }

    @Test
    void testPatchRepeatedIdChangesNothing() {
        LegalTask task = this.createTask();
        this.restTestClient.patch().uri(LegalTaskResource.LEGAL_TASKS)
                .body(List.of(new LegalTaskStatusUpdate(task.getId(), TaskStatus.WITHDRAWN),
                        new LegalTaskStatusUpdate(task.getId(), TaskStatus.DEPRECATED)))
                .exchange()
                .expectStatus().isBadRequest();
        this.assertStatus(task.getId(), TaskStatus.CURRENT);
    }

    @Test
    void testPatchEmptyList() {
        this.restTestClient.patch().uri(LegalTaskResource.LEGAL_TASKS)
                .body(List.of())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatchMissingId() {
        this.restTestClient.patch().uri(LegalTaskResource.LEGAL_TASKS)
                .body(List.of(new LegalTaskStatusUpdate(null, TaskStatus.WITHDRAWN)))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatchMissingStatus() {
        this.restTestClient.patch().uri(LegalTaskResource.LEGAL_TASKS)
                .body(List.of(new LegalTaskStatusUpdate(ID_0, null)))
                .exchange()
                .expectStatus().isBadRequest();
    }

    private LegalTask createTask() {
        return this.restTestClient.post().uri(LegalTaskResource.LEGAL_TASKS)
                .body(LegalTask.builder().title("FT task " + UUID.randomUUID()).notes("Original notes").build())
                .exchange().expectStatus().isCreated()
                .expectBody(LegalTask.class).returnResult().getResponseBody();
    }

    private void assertStatus(UUID id, TaskStatus taskStatus) {
        this.restTestClient.get().uri(LegalTaskResource.LEGAL_TASKS + "/" + id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalTask.class)
                .value(body -> assertThat(body).isNotNull().extracting(LegalTask::getTaskStatus)
                        .isEqualTo(taskStatus));
    }

}
