package es.upm.miw.apaw.domain.services.taskmanagement;

import es.upm.miw.apaw.adapters.out.taskmanagement.postgres.TaskCommentEntity;
import es.upm.miw.apaw.adapters.out.taskmanagement.postgres.TaskEntity;
import es.upm.miw.apaw.adapters.out.taskmanagement.postgres.TaskRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.taskmanagement.CreationTask;
import es.upm.miw.apaw.domain.model.taskmanagement.Task;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.TaskManagementSeederForDev.COMMENT_ID_0;
import static es.upm.miw.apaw.config.seeders.TaskManagementSeederForDev.COMMENT_ID_1;
import static es.upm.miw.apaw.config.seeders.TaskManagementSeederForDev.TASK_0;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class TaskServiceIT {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @MockitoBean
    private UserFinder userFinder;

    @Test
    @Transactional
    void testCreate() {
        UserSnapshot owner = this.user();

        CreationTask creation = CreationTask.builder()
                .title("Task " + UUID.randomUUID())
                .description("Integration test task")
                .dueDate(LocalDate.of(2026, 12, 1))
                .priority(2)
                .completion(false)
                .estimatedHours(new BigDecimal("5.50"))
                .taskCommentIds(List.of(COMMENT_ID_0, COMMENT_ID_1))
                .ownerId(owner.getId())
                .build();

        when(this.userFinder.read(owner.getId())).thenReturn(owner);

        Task task = this.taskService.create(creation);

        assertThat(task.getId()).isNotNull();
        assertThat(task.getTitle()).isEqualTo(creation.getTitle());
        assertThat(task.getDescription()).isEqualTo(creation.getDescription());
        assertThat(task.getDueDate()).isEqualTo(creation.getDueDate());
        assertThat(task.getPriority()).isEqualTo(2);
        assertThat(task.getCompletion()).isFalse();
        assertThat(task.getEstimatedHours()).isEqualByComparingTo(new BigDecimal("5.50"));

        assertThat(task.getComments()).extracting(comment -> comment.getId())
                .containsExactly(COMMENT_ID_0, COMMENT_ID_1);

        assertThat(task.getOwner()).isEqualTo(owner);

        TaskEntity entity = this.taskRepository.findById(task.getId()).orElseThrow();

        assertThat(entity.getTitle()).isEqualTo(creation.getTitle());

        assertThat(entity.getComments()).extracting(TaskCommentEntity::getId)
                .containsExactly(COMMENT_ID_0, COMMENT_ID_1);

        assertThat(entity.getOwnerId()).isEqualTo(owner.getId());

        verify(this.userFinder, times(1)).read(owner.getId());
    }

    @Test
    @Transactional
    void testCreateDefaults() {
        UserSnapshot owner = this.user();
        CreationTask creation = this.creation(owner.getId());
        creation.setTaskCommentIds(List.of());

        when(this.userFinder.read(owner.getId())).thenReturn(owner);

        Task task = this.taskService.create(creation);

        assertThat(task.getId()).isNotNull();
        assertThat(task.getPriority()).isEqualTo(3);
        assertThat(task.getCompletion()).isFalse();
        assertThat(task.getComments()).isEmpty();
        assertThat(task.getOwner()).isEqualTo(owner);

        verify(this.userFinder, times(1)).read(owner.getId());
    }

    @Test
    @Transactional
    void testCreateDuplicateTitle() {
        UserSnapshot owner = this.user();
        CreationTask creation = this.creation(owner.getId());
        creation.setTitle(TASK_0.getTitle());

        assertThatThrownBy(() -> this.taskService.create(creation)).isInstanceOf(ConflictException.class)
                .hasMessageContaining(TASK_0.getTitle());

        verifyNoInteractions(this.userFinder);
    }

    @Test
    @Transactional
    void testCreateTaskCommentNotFound() {
        UserSnapshot owner = this.user();
        UUID missingCommentId = UUID.randomUUID();

        CreationTask creation = this.creation(owner.getId());
        creation.setTaskCommentIds(List.of(COMMENT_ID_0, missingCommentId));

        assertThatThrownBy(() -> this.taskService.create(creation)).isInstanceOf(NotFoundException.class)
                .hasMessageContaining(missingCommentId.toString());

        verifyNoInteractions(this.userFinder);
    }

    @Test
    @Transactional
    void testCreateOwnerNotFound() {
        UserSnapshot owner = this.user();
        CreationTask creation = this.creation(owner.getId());

        when(this.userFinder.read(owner.getId()))
                .thenThrow(new NotFoundException("User id not found: " + owner.getId()));

        assertThatThrownBy(() -> this.taskService.create(creation)).isInstanceOf(NotFoundException.class)
                .hasMessageContaining(owner.getId().toString());

        assertThat(this.taskRepository.existsByTitle(creation.getTitle())).isFalse();

        verify(this.userFinder, times(1)).read(owner.getId());
    }

    private UserSnapshot user() {
        return UserSnapshot.builder()
                .id(UUID.randomUUID())
                .firstName("John")
                .familyName("Doe")
                .email("john.doe@example.com")
                .build();
    }

    private CreationTask creation(UUID ownerId) {
        return CreationTask.builder()
                .title("Task " + UUID.randomUUID())
                .dueDate(LocalDate.of(2026, 12, 1))
                .taskCommentIds(List.of(COMMENT_ID_0))
                .ownerId(ownerId)
                .build();
    }
}