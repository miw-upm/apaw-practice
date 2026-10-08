package es.upm.miw.apaw.adapters.out.taskmanagement.postgres;

import es.upm.miw.apaw.domain.model.taskmanagement.Task;
import es.upm.miw.apaw.domain.ports.out.taskmanagement.TaskGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import es.upm.miw.apaw.domain.model.taskmanagement.CommentType;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskActivityReport;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskFindCriteria;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TaskAdapter implements TaskGateway {

    private final TaskRepository taskRepository;
    private final TaskCommentRepository taskCommentRepository;

    @Override
    @Transactional
    public Task create(Task task) {
        TaskEntity taskEntity = new TaskEntity(task);

        List<TaskCommentEntity> taskCommentEntities = task.getComments().stream()
                .map(taskComment -> this.taskCommentRepository.getReferenceById(taskComment.getId()))
                .collect(Collectors.toCollection(ArrayList::new));

        taskEntity.setComments(taskCommentEntities);

        this.taskRepository.save(taskEntity);

        return task;
    }

    @Override
    public boolean existsByTitle(String title) {
        return this.taskRepository.existsByTitle(title);
    }

    @Override
    public List<TaskActivityReport> findActivityReport() {
        return this.taskRepository.findActivityReport(CommentType.IMPORTANT);
    }

    @Override
    public List<Task> find(TaskFindCriteria criteria) {
        Specification<TaskEntity> specification = this.buildSpecification(criteria);

        return this.taskRepository.findAll(specification, Sort.by("title")).stream()
                .map(this::toDomainWithoutComments)
                .toList();
    }

    private Task toDomainWithoutComments(TaskEntity entity) {
        Task task = new Task();
        BeanUtils.copyProperties(entity, task, "comments", "ownerId");
        task.setOwner(UserSnapshot.builder().id(entity.getOwnerId()).build());
        return task;
    }

    private Specification<TaskEntity> buildSpecification(TaskFindCriteria criteria) {
        Specification<TaskEntity> specification = (root, query, builder) -> builder.conjunction();

        if (criteria.hasPriority()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("priority"), criteria.getPriority()));
        }

        if (criteria.hasOverdue()) {
            specification = specification.and((root, query, builder) -> {
                var overdue = builder.and(
                        builder.isFalse(root.get("completion")),
                        builder.lessThan(root.<LocalDate>get("dueDate"), LocalDate.now())
                );

                return criteria.getOverdue() ? overdue : builder.not(overdue);
            });
        }

        specification = this.addType(specification, criteria.getType());

        return specification;
    }

    private Specification<TaskEntity> addType(Specification<TaskEntity> specification, CommentType type) {

        if (type == null) {
            return specification;
        }

        return specification.and((root, query, builder) -> {
            query.distinct(true);
            return builder.equal(root.join("comments").get("type"), type);
        });
    }
}
