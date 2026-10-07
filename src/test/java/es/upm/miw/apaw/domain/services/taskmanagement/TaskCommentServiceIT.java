package es.upm.miw.apaw.domain.services.taskmanagement;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.taskmanagement.CommentType;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskComment;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskCommentEditionAndTypeUpdate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.TaskManagementSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class TaskCommentServiceIT {

    @Autowired
    private TaskCommentService taskCommentService;

    @Test
    void testReadSeeder() {
        TaskComment taskComment = this.taskCommentService.read(COMMENT_ID_0);
        assertThat(taskComment)
                .usingRecursiveComparison()
                .ignoringFields("author")
                .isEqualTo(COMMENT_0);
        assertThat(taskComment.getAuthor().getId()).isEqualTo(COMMENT_0.getAuthor().getId());
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();

        assertThatThrownBy(() -> this.taskCommentService.read(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void testFindAllAllowsAdditionalComments() {
        TaskComment extra = this.createComment();

        List<TaskComment> comments = this.taskCommentService.findAll();

        assertThat(comments)
                .extracting(TaskComment::getId)
                .contains(COMMENT_ID_0, COMMENT_ID_1, COMMENT_ID_2, COMMENT_ID_3, extra.getId());

        assertThat(comments)
                .extracting(TaskComment::getId)
                .containsSubsequence(COMMENT_ID_3, COMMENT_ID_2, COMMENT_ID_1, COMMENT_ID_0);

        assertThat(this.taskCommentService.findAll())
                .extracting(TaskComment::getId)
                .containsExactlyElementsOf(comments.stream().map(TaskComment::getId).toList());
    }

    @Test
    void testCreate() {
        TaskComment comment = this.createComment();

        TaskComment stored = this.taskCommentService.read(comment.getId());

        assertThat(stored).usingRecursiveComparison().ignoringFields("creationDate").isEqualTo(comment);

        assertThat(stored.getEdition()).isFalse();
        assertThat(stored.getAttachment()).isFalse();
        assertThat(stored.getType()).isEqualTo(CommentType.GENERAL);
        assertThat(stored.getCreationDate()).isNotNull();
    }

    @Test
    void testUpdateReplacesMutableFields() {
        TaskComment original = this.createComment();

        TaskComment replacement = TaskComment.builder()
                .content("Updated content")
                .edition(true)
                .attachment(true)
                .type(CommentType.IMPORTANT)
                .author(COMMENT_1.getAuthor())
                .build();

        this.taskCommentService.update(original.getId(), replacement);

        TaskComment updated = this.taskCommentService.read(original.getId());

        assertThat(updated.getContent()).isEqualTo("Updated content");
        assertThat(updated.getEdition()).isTrue();
        assertThat(updated.getAttachment()).isTrue();
        assertThat(updated.getType()).isEqualTo(CommentType.IMPORTANT);
        assertThat(updated.getAuthor().getId()).isEqualTo(COMMENT_1.getAuthor().getId());
        assertThat(updated.getId()).isEqualTo(original.getId());
    }

    @Test
    void testUpdateAppliesDefaults() {
        TaskComment original = this.createComment();

        TaskComment replacement = TaskComment.builder()
                .content("Updated with defaults")
                .author(COMMENT_2.getAuthor())
                .build();

        this.taskCommentService.update(original.getId(), replacement);

        TaskComment updated = this.taskCommentService.read(original.getId());

        assertThat(updated.getEdition()).isFalse();
        assertThat(updated.getAttachment()).isFalse();
        assertThat(updated.getType()).isEqualTo(CommentType.GENERAL);
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();

        assertThatThrownBy(() -> this.taskCommentService.update(id, COMMENT_0))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void testDelete() {
        TaskComment comment = this.createComment();

        this.taskCommentService.delete(comment.getId());

        assertThatThrownBy(() -> this.taskCommentService.read(comment.getId()))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteMissingComment() {
        UUID id = UUID.randomUUID();

        this.taskCommentService.delete(id);

        assertThatThrownBy(() -> this.taskCommentService.read(id))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteReferencedComment() {
        assertThatThrownBy(() -> this.taskCommentService.delete(COMMENT_ID_0))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(COMMENT_ID_0.toString());

        assertThat(this.taskCommentService.read(COMMENT_ID_0).getId())
                .isEqualTo(COMMENT_ID_0);
    }

    @Test
    void testUpdateEditionAndType() {
        TaskComment first = this.createComment();
        TaskComment second = this.createComment();

        this.taskCommentService.updateEditionAndType(List.of(
                new TaskCommentEditionAndTypeUpdate(
                        first.getId(),
                        true,
                        CommentType.IMPORTANT
                ),
                new TaskCommentEditionAndTypeUpdate(
                        second.getId(),
                        true,
                        CommentType.INTERNAL
                )));

        TaskComment updatedFirst = this.taskCommentService.read(first.getId());

        TaskComment updatedSecond = this.taskCommentService.read(second.getId());

        assertThat(updatedFirst.getEdition()).isTrue();
        assertThat(updatedFirst.getType()).isEqualTo(CommentType.IMPORTANT);

        assertThat(updatedSecond.getEdition()).isTrue();
        assertThat(updatedSecond.getType()).isEqualTo(CommentType.INTERNAL);
    }

    @Test
    void testUpdateEditionAndTypeNotFoundChangesNothing() {
        TaskComment comment = this.createComment();
        UUID missingId = UUID.randomUUID();

        assertThatThrownBy(() -> this.taskCommentService.updateEditionAndType(List.of(
                        new TaskCommentEditionAndTypeUpdate(
                                comment.getId(),
                                true,
                                CommentType.IMPORTANT
                        ),
                        new TaskCommentEditionAndTypeUpdate(
                                missingId,
                                true,
                                CommentType.INTERNAL
                        ))))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(missingId.toString());

        TaskComment stored = this.taskCommentService.read(comment.getId());

        assertThat(stored.getEdition()).isFalse();
        assertThat(stored.getType()).isEqualTo(CommentType.GENERAL);
    }

    @Test
    void testUpdateEditionAndTypeDuplicateIdChangesNothing() {
        TaskComment comment = this.createComment();

        assertThatThrownBy(() -> this.taskCommentService.updateEditionAndType(List.of(
                        new TaskCommentEditionAndTypeUpdate(
                                comment.getId(),
                                true,
                                CommentType.IMPORTANT
                        ),
                        new TaskCommentEditionAndTypeUpdate(
                                comment.getId(),
                                false,
                                CommentType.INTERNAL
                        ))))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(comment.getId().toString());

        TaskComment stored = this.taskCommentService.read(comment.getId());

        assertThat(stored.getEdition()).isFalse();
        assertThat(stored.getType()).isEqualTo(CommentType.GENERAL);
    }

    private TaskComment createComment() {
        return this.taskCommentService.create(
                TaskComment.builder()
                        .content("IT comment " + UUID.randomUUID())
                        .author(COMMENT_0.getAuthor())
                        .build()
        );
    }
}
