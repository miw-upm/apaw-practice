package es.upm.miw.apaw.domain.services.taskmanagement;

import es.upm.miw.apaw.adapters.out.taskmanagement.postgres.TaskCommentEntity;
import es.upm.miw.apaw.adapters.out.taskmanagement.postgres.TaskEntity;
import es.upm.miw.apaw.adapters.out.taskmanagement.postgres.TaskRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.taskmanagement.CreationTask;
import es.upm.miw.apaw.domain.model.taskmanagement.Task;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskActivityReport;
import es.upm.miw.apaw.domain.model.taskmanagement.CommentType;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskFindCriteria;
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
import java.util.Set;
import java.util.Map;

import static es.upm.miw.apaw.config.seeders.TaskManagementSeederForDev.*;
import static org.mockito.ArgumentMatchers.anySet;
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

    @Test
    @Transactional
    void testFindActivityReport() {
        when(this.userFinder.findByIds(anySet()))
                .thenAnswer(invocation -> {
                    Set<UUID> ids = invocation.getArgument(0);
                    return ids.stream().map(this::reportUser).toList();
                });

        List<TaskActivityReport> reports = this.taskService.findActivityReport();

        assertThat(reports)
                .extracting(TaskActivityReport::getTaskTitle)
                .containsSubsequence(TASK_0.getTitle(), TASK_1.getTitle(), TASK_2.getTitle());

        TaskActivityReport firstReport = reports.stream()
                .filter(report -> report.getTaskTitle().equals(TASK_0.getTitle()))
                .findFirst().orElseThrow();

        assertThat(firstReport.getTotalCommentCount()).isEqualTo(2);
        assertThat(firstReport.getImportantCommentCount()).isEqualTo(1);
        assertThat(firstReport.getEditedCommentCount()).isEqualTo(1);
        assertThat(firstReport.getAttachmentCommentCount()).isZero();
        assertThat(firstReport.getOwner()).isEqualTo(TASK_0.getOwner());

        TaskActivityReport secondReport = reports.stream()
                .filter(report -> report.getTaskTitle().equals(TASK_1.getTitle()))
                .findFirst().orElseThrow();

        assertThat(secondReport.getTotalCommentCount()).isEqualTo(1);
        assertThat(secondReport.getImportantCommentCount()).isZero();
        assertThat(secondReport.getEditedCommentCount()).isZero();
        assertThat(secondReport.getAttachmentCommentCount()).isEqualTo(1);
        assertThat(secondReport.getOwner()).isEqualTo(TASK_1.getOwner());

        TaskActivityReport thirdReport = reports.stream()
                .filter(report -> report.getTaskTitle().equals(TASK_2.getTitle()))
                .findFirst().orElseThrow();

        assertThat(thirdReport.getTotalCommentCount()).isEqualTo(1);
        assertThat(thirdReport.getImportantCommentCount()).isZero();
        assertThat(thirdReport.getEditedCommentCount()).isZero();
        assertThat(thirdReport.getAttachmentCommentCount()).isZero();
        assertThat(thirdReport.getOwner()).isEqualTo(TASK_2.getOwner());

        verify(this.userFinder, times(1)).findByIds(anySet());
    }

    @Test
    @Transactional
    void testFindActivityReportOwnerNotFound() {
        UUID missingOwnerId = TASK_2.getOwner().getId();

        when(this.userFinder.findByIds(anySet()))
                .thenAnswer(invocation -> {
                    Set<UUID> ids = invocation.getArgument(0);
                    return ids.stream().filter(id -> !id.equals(missingOwnerId))
                            .map(this::reportUser).toList();
                });

        assertThatThrownBy(() -> this.taskService.findActivityReport()).isInstanceOf(NotFoundException.class)
                .hasMessageContaining(missingOwnerId.toString());

        verify(this.userFinder, times(1)).findByIds(anySet());
    }

    @Test
    @Transactional
    void testFindByPriority() {
        this.mockFindUsers();

        List<Task> tasks = this.taskService.find(
                TaskFindCriteria.builder()
                        .priority(TASK_1.getPriority())
                        .build()
        );

        assertThat(tasks).extracting(Task::getId).contains(TASK_ID_1).doesNotContain(TASK_ID_0, TASK_ID_2);

        verify(this.userFinder, times(1)).findByIds(anySet());
    }

    @Test
    @Transactional
    void testFindByOverdue() {
        this.mockFindUsers();

        List<Task> tasks = this.taskService.find(TaskFindCriteria.builder().overdue(true).build());

        assertThat(tasks).extracting(Task::getId).contains(TASK_ID_0, TASK_ID_1).doesNotContain(TASK_ID_2);

        verify(this.userFinder, times(1)).findByIds(anySet());
    }

    @Test
    @Transactional
    void testFindByType() {
        this.mockFindUsers();

        List<Task> tasks = this.taskService.find(TaskFindCriteria.builder().type(CommentType.IMPORTANT).build());

        assertThat(tasks).extracting(Task::getId).contains(TASK_ID_0).doesNotContain(TASK_ID_1, TASK_ID_2);

        verify(this.userFinder, times(1)).findByIds(anySet());
    }

    @Test
    @Transactional
    void testFindByOwnerFirstName() {
        this.mockFindUsers();

        List<Task> tasks = this.taskService.find(
                TaskFindCriteria.builder()
                        .ownerFirstName(TASK_0.getOwner().getFirstName())
                        .build()
        );

        assertThat(tasks).extracting(Task::getId).contains(TASK_ID_0).doesNotContain(TASK_ID_1, TASK_ID_2);

        assertThat(tasks)
                .filteredOn(task -> task.getId().equals(TASK_ID_0))
                .singleElement()
                .extracting(Task::getOwner)
                .isEqualTo(TASK_0.getOwner());

        verify(this.userFinder, times(1)).findByIds(anySet());
    }

    @Test
    @Transactional
    void testFindByCombinedCriteria() {
        this.mockFindUsers();

        List<Task> tasks = this.taskService.find(
                TaskFindCriteria.builder()
                        .priority(TASK_0.getPriority())
                        .overdue(true)
                        .type(CommentType.IMPORTANT)
                        .ownerFirstName(TASK_0.getOwner().getFirstName())
                        .build()
        );

        assertThat(tasks).extracting(Task::getId).containsExactly(TASK_ID_0);

        verify(this.userFinder, times(1)).findByIds(anySet());
    }

    @Test
    @Transactional
    void testFindAllCriteriaNullSafe() {
        this.mockFindUsers();

        List<Task> tasks = this.taskService.find(TaskFindCriteria.builder().build());

        assertThat(tasks).extracting(Task::getId).contains(TASK_ID_0, TASK_ID_1, TASK_ID_2);

        verify(this.userFinder, times(1)).findByIds(anySet());
    }

    @Test
    @Transactional
    void testFindEmptyDoesNotCallUserFinder() {
        List<Task> tasks = this.taskService.find(TaskFindCriteria.builder().priority(Integer.MAX_VALUE).build());

        assertThat(tasks).isEmpty();

        verifyNoInteractions(this.userFinder);
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

    private UserSnapshot reportUser(UUID id) {
        if (id.equals(TASK_0.getOwner().getId())) {
            return TASK_0.getOwner();
        }
        if (id.equals(TASK_1.getOwner().getId())) {
            return TASK_1.getOwner();
        }
        if (id.equals(TASK_2.getOwner().getId())) {
            return TASK_2.getOwner();
        }

        return UserSnapshot.builder()
                .id(id)
                .firstName("Seeder")
                .familyName("User")
                .email("seeder.user@example.com")
                .build();
    }

    private void mockFindUsers() {
        Map<UUID, UserSnapshot> users = Map.of(
                TASK_0.getOwner().getId(), TASK_0.getOwner(),
                TASK_1.getOwner().getId(), TASK_1.getOwner(),
                TASK_2.getOwner().getId(), TASK_2.getOwner()
        );

        when(this.userFinder.findByIds(anySet()))
                .thenAnswer(invocation -> {
                    Set<UUID> ids = invocation.getArgument(0);

                    return ids.stream()
                            .map(id -> users.getOrDefault(
                                    id,
                                    UserSnapshot.builder()
                                            .id(id)
                                            .firstName("Additional")
                                            .familyName("User")
                                            .email("additional.user@example.com")
                                            .build()
                            ))
                            .toList();
                });
    }
}