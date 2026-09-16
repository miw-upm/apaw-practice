package es.upm.miw.apaw.domain.services.legalprocedure;

import es.upm.miw.apaw.adapters.out.legalprocedure.postgres.LegalProcedureEntity;
import es.upm.miw.apaw.adapters.out.legalprocedure.postgres.LegalProcedureRepository;
import es.upm.miw.apaw.adapters.out.legalprocedure.postgres.LegalTaskEntity;
import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalTask;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalTaskStatusUpdate;
import es.upm.miw.apaw.domain.models.legalprocedure.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.LegalProcedureSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class LegalTaskServiceIT {
    @Autowired
    private LegalTaskService legalTaskService;
    @Autowired
    private LegalProcedureRepository legalProcedureRepository;

    @Test
    void testReadSeeder() {
        assertThat(this.legalTaskService.read(ID_0)).usingRecursiveComparison().isEqualTo(TASK_0);
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.legalTaskService.read(id))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testFindAllAllowsAdditionalTasks() {
        LegalTask extra = this.createTask();
        List<LegalTask> tasks = this.legalTaskService.findAll();
        assertThat(tasks).extracting(LegalTask::getId).contains(ID_0, ID_1, ID_2, ID_3, ID_4, ID_5, extra.getId());
        assertThat(tasks).extracting(LegalTask::getTitle).containsSubsequence(
                TASK_1.getTitle(), TASK_2.getTitle(), TASK_4.getTitle(),
                TASK_0.getTitle(), TASK_5.getTitle(), TASK_3.getTitle());
        assertThat(this.legalTaskService.findAll()).extracting(LegalTask::getId)
                .containsExactlyElementsOf(tasks.stream().map(LegalTask::getId).toList());
    }

    @Test
    void testCreate() {
        LegalTask task = this.createTask();
        LegalTask stored = this.legalTaskService.read(task.getId());
        assertThat(stored).usingRecursiveComparison().ignoringFields("creatingDate").isEqualTo(task);
        assertThat(stored.getTaskStatus()).isEqualTo(TaskStatus.CURRENT);
    }

    @Test
    void testCreateDuplicateTitle() {
        LegalTask task = LegalTask.builder().title(TASK_0.getTitle()).build();
        assertThatThrownBy(() -> this.legalTaskService.create(task))
                .isInstanceOf(ConflictException.class).hasMessageContaining(TASK_0.getTitle());
    }

    @Test
    void testUpdateReplacesMutableFields() {
        LegalTask original = this.createTask();
        LegalTask replacement = LegalTask.builder().title("Updated " + UUID.randomUUID())
                .taskStatus(TaskStatus.WITHDRAWN).build();
        this.legalTaskService.update(original.getId(), replacement);
        LegalTask updated = this.legalTaskService.read(original.getId());
        assertThat(updated.getTitle()).isEqualTo(replacement.getTitle());
        assertThat(updated.getNotes()).isNull();
        assertThat(updated.getTaskStatus()).isEqualTo(TaskStatus.WITHDRAWN);
        assertThat(updated.getId()).isEqualTo(original.getId());
    }

    @Test
    void testUpdateSameTitle() {
        LegalTask task = this.createTask();
        task.setNotes("Updated notes");
        this.legalTaskService.update(task.getId(), task);
        assertThat(this.legalTaskService.read(task.getId()).getNotes()).isEqualTo("Updated notes");
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.legalTaskService.update(id, TASK_0))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testUpdateDuplicateTitleLeavesTaskUnchanged() {
        LegalTask task = this.createTask();
        assertThatThrownBy(() -> this.legalTaskService.update(task.getId(), TASK_0))
                .isInstanceOf(ConflictException.class).hasMessageContaining(TASK_0.getTitle());
        assertThat(this.legalTaskService.read(task.getId()).getTitle()).isEqualTo(task.getTitle());
    }

    @Test
    void testDelete() {
        LegalTask task = this.createTask();
        this.legalTaskService.delete(task.getId());
        assertThatThrownBy(() -> this.legalTaskService.read(task.getId())).isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteMissingTask() {
        UUID id = UUID.randomUUID();
        this.legalTaskService.delete(id);
        assertThatThrownBy(() -> this.legalTaskService.read(id)).isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteReferencedTask() {
        LegalTask task = this.createTask();
        LegalProcedureEntity procedure = LegalProcedureEntity.builder().id(UUID.randomUUID())
                .title("Procedure " + UUID.randomUUID()).startedDate(LocalDate.of(2025, 1, 1))
                .budget(BigDecimal.TEN).vatIncluded(false).userId(UUID.randomUUID())
                .legalTasks(List.of(new LegalTaskEntity(task))).build();
        this.legalProcedureRepository.saveAndFlush(procedure);
        assertThatThrownBy(() -> this.legalTaskService.delete(task.getId()))
                .isInstanceOf(ConflictException.class).hasMessageContaining(task.getId().toString());
        assertThat(this.legalTaskService.read(task.getId()).getId()).isEqualTo(task.getId());
        assertThat(this.legalProcedureRepository.existsByLegalTasksId(task.getId())).isTrue();
    }

    @Test
    void testUpdateTaskStatuses() {
        LegalTask first = this.createTask();
        LegalTask second = this.createTask();
        this.legalTaskService.updateTaskStatuses(List.of(
                new LegalTaskStatusUpdate(first.getId(), TaskStatus.WITHDRAWN),
                new LegalTaskStatusUpdate(second.getId(), TaskStatus.DEPRECATED)));
        assertThat(this.legalTaskService.read(first.getId()).getTaskStatus()).isEqualTo(TaskStatus.WITHDRAWN);
        assertThat(this.legalTaskService.read(second.getId()).getTaskStatus()).isEqualTo(TaskStatus.DEPRECATED);
    }

    @Test
    void testUpdateTaskStatusesNotFoundChangesNothing() {
        LegalTask task = this.createTask();
        UUID missingId = UUID.randomUUID();
        assertThatThrownBy(() -> this.legalTaskService.updateTaskStatuses(List.of(
                new LegalTaskStatusUpdate(task.getId(), TaskStatus.WITHDRAWN),
                new LegalTaskStatusUpdate(missingId, TaskStatus.DEPRECATED))))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(missingId.toString());
        assertThat(this.legalTaskService.read(task.getId()).getTaskStatus()).isEqualTo(TaskStatus.CURRENT);
    }

    @Test
    void testUpdateTaskStatusesDuplicateIdChangesNothing() {
        LegalTask task = this.createTask();
        assertThatThrownBy(() -> this.legalTaskService.updateTaskStatuses(List.of(
                new LegalTaskStatusUpdate(task.getId(), TaskStatus.WITHDRAWN),
                new LegalTaskStatusUpdate(task.getId(), TaskStatus.DEPRECATED))))
                .isInstanceOf(BadRequestException.class).hasMessageContaining(task.getId().toString());
        assertThat(this.legalTaskService.read(task.getId()).getTaskStatus()).isEqualTo(TaskStatus.CURRENT);
    }

    private LegalTask createTask() {
        return this.legalTaskService.create(LegalTask.builder().title("IT task " + UUID.randomUUID())
                .creatingDate(TASK_0.getCreatingDate()).notes("Original notes").build());
    }
}
