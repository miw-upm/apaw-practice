package es.upm.miw.apaw.functionaltests.taskmanagement;

import es.upm.miw.apaw.adapters.in.taskmanagement.TaskCommentResource;
import es.upm.miw.apaw.domain.model.taskmanagement.CommentType;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskComment;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskCommentEditionAndTypeUpdate;
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

import static es.upm.miw.apaw.config.seeders.TaskManagementSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class TaskCommentResourceFT {

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
        this.restTestClient.get()
                .uri(TaskCommentResource.TASK_COMMENTS + "/" + COMMENT_ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TaskComment.class)
                .value(body -> {
                    assertThat(body).isNotNull();

                    assertThat(body)
                            .usingRecursiveComparison()
                            .ignoringFields("author")
                            .isEqualTo(COMMENT_0);

                    assertThat(body.getAuthor().getId())
                            .isEqualTo(COMMENT_0.getAuthor().getId());
                });
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();

        this.restTestClient.get()
                .uri(TaskCommentResource.TASK_COMMENTS + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body ->
                        assertThat((String) body.get("message")).contains(id.toString()));
    }

    @Test
    void testFindAll() {
        this.restTestClient.get()
                .uri(TaskCommentResource.TASK_COMMENTS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TaskComment[].class)
                .value(body ->
                        assertThat(body)
                                .extracting(TaskComment::getId)
                                .containsSubsequence(COMMENT_ID_3, COMMENT_ID_2, COMMENT_ID_1, COMMENT_ID_0));
    }

    @Test
    void testCreateDefaults() {
        this.restTestClient.post()
                .uri(TaskCommentResource.TASK_COMMENTS)
                .body(TaskComment.builder()
                        .content("FT comment " + UUID.randomUUID())
                        .author(COMMENT_0.getAuthor()).build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(TaskComment.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getCreationDate()).isNotNull();
                    assertThat(body.getEdition()).isFalse();
                    assertThat(body.getAttachment()).isFalse();
                    assertThat(body.getType()).isEqualTo(CommentType.GENERAL);
                    assertThat(body.getAuthor().getId()).isEqualTo(COMMENT_0.getAuthor().getId());
                });
    }

    @Test
    void testCreateBlankContent() {
        this.restTestClient.post()
                .uri(TaskCommentResource.TASK_COMMENTS)
                .body(TaskComment.builder()
                        .content(" ")
                        .author(COMMENT_0.getAuthor()).build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateMissingAuthor() {
        this.restTestClient.post()
                .uri(TaskCommentResource.TASK_COMMENTS)
                .body(TaskComment.builder()
                        .content("Comment without author").build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testUpdate() {
        TaskComment comment = this.createComment();

        TaskComment replacement = TaskComment.builder()
                .content("Updated content")
                .edition(true)
                .attachment(true)
                .type(CommentType.IMPORTANT)
                .author(COMMENT_1.getAuthor())
                .build();

        this.restTestClient.put()
                .uri(TaskCommentResource.TASK_COMMENTS + "/" + comment.getId())
                .body(replacement)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TaskComment.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(comment.getId());
                    assertThat(body.getContent()).isEqualTo(replacement.getContent());
                    assertThat(body.getEdition()).isTrue();
                    assertThat(body.getAttachment()).isTrue();
                    assertThat(body.getType()).isEqualTo(CommentType.IMPORTANT);
                    assertThat(body.getAuthor().getId()).isEqualTo(COMMENT_1.getAuthor().getId());
                });
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();

        TaskComment replacement = TaskComment.builder()
                .content("Missing comment")
                .edition(false)
                .attachment(false)
                .type(CommentType.GENERAL)
                .author(COMMENT_0.getAuthor())
                .build();

        this.restTestClient.put()
                .uri(TaskCommentResource.TASK_COMMENTS + "/" + id)
                .body(replacement)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDelete() {
        TaskComment comment = this.createComment();

        this.restTestClient.delete()
                .uri(TaskCommentResource.TASK_COMMENTS + "/" + comment.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();

        this.restTestClient.get()
                .uri(TaskCommentResource.TASK_COMMENTS + "/" + comment.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteReferencedComment() {
        this.restTestClient.delete()
                .uri(TaskCommentResource.TASK_COMMENTS + "/" + COMMENT_ID_0)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);

        this.restTestClient.get()
                .uri(TaskCommentResource.TASK_COMMENTS + "/" + COMMENT_ID_0)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testPatch() {
        TaskComment first = this.createComment();
        TaskComment second = this.createComment();

        this.restTestClient.patch()
                .uri(TaskCommentResource.TASK_COMMENTS)
                .body(List.of(
                        new TaskCommentEditionAndTypeUpdate(
                                first.getId(),
                                true,
                                CommentType.IMPORTANT
                        ),
                        new TaskCommentEditionAndTypeUpdate(
                                second.getId(),
                                true,
                                CommentType.INTERNAL
                        )))
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();

        this.assertEditionAndType(
                first.getId(),
                true,
                CommentType.IMPORTANT);

        this.assertEditionAndType(
                second.getId(),
                true,
                CommentType.INTERNAL);
    }

    @Test
    void testPatchNotFoundChangesNothing() {
        TaskComment comment = this.createComment();
        UUID missingId = UUID.randomUUID();

        this.restTestClient.patch()
                .uri(TaskCommentResource.TASK_COMMENTS)
                .body(List.of(
                        new TaskCommentEditionAndTypeUpdate(
                                comment.getId(),
                                true,
                                CommentType.IMPORTANT
                        ),
                        new TaskCommentEditionAndTypeUpdate(
                                missingId,
                                true,
                                CommentType.INTERNAL
                        )))
                .exchange()
                .expectStatus().isNotFound();

        this.assertEditionAndType(
                comment.getId(),
                false,
                CommentType.GENERAL);
    }

    @Test
    void testPatchRepeatedIdChangesNothing() {
        TaskComment comment = this.createComment();

        this.restTestClient.patch()
                .uri(TaskCommentResource.TASK_COMMENTS)
                .body(List.of(
                        new TaskCommentEditionAndTypeUpdate(
                                comment.getId(),
                                true,
                                CommentType.IMPORTANT
                        ),
                        new TaskCommentEditionAndTypeUpdate(
                                comment.getId(),
                                false,
                                CommentType.INTERNAL
                        )))
                .exchange()
                .expectStatus().isBadRequest();

        this.assertEditionAndType(
                comment.getId(),
                false,
                CommentType.GENERAL);
    }

    @Test
    void testPatchEmptyList() {
        this.restTestClient.patch()
                .uri(TaskCommentResource.TASK_COMMENTS)
                .body(List.of())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatchMissingId() {
        this.restTestClient.patch()
                .uri(TaskCommentResource.TASK_COMMENTS)
                .body(List.of(
                        new TaskCommentEditionAndTypeUpdate(
                                null,
                                true,
                                CommentType.IMPORTANT
                        )))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatchMissingEdition() {
        this.restTestClient.patch()
                .uri(TaskCommentResource.TASK_COMMENTS)
                .body(List.of(
                        new TaskCommentEditionAndTypeUpdate(
                                COMMENT_ID_0,
                                null,
                                CommentType.IMPORTANT
                        )))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatchMissingType() {
        this.restTestClient.patch()
                .uri(TaskCommentResource.TASK_COMMENTS)
                .body(List.of(
                        new TaskCommentEditionAndTypeUpdate(
                                COMMENT_ID_0,
                                true,
                                null
                        )))
                .exchange()
                .expectStatus().isBadRequest();
    }

    private TaskComment createComment() {
        return this.restTestClient.post()
                .uri(TaskCommentResource.TASK_COMMENTS)
                .body(TaskComment.builder()
                        .content("FT comment " + UUID.randomUUID())
                        .author(COMMENT_0.getAuthor())
                        .build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(TaskComment.class)
                .returnResult()
                .getResponseBody();
    }

    private void assertEditionAndType(
            UUID id,
            Boolean edition,
            CommentType type) {

        this.restTestClient.get()
                .uri(TaskCommentResource.TASK_COMMENTS + "/" + id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TaskComment.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getEdition()).isEqualTo(edition);
                    assertThat(body.getType()).isEqualTo(type);
                });
    }
}
