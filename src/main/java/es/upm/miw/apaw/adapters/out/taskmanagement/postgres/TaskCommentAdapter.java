package es.upm.miw.apaw.adapters.out.taskmanagement.postgres;

import es.upm.miw.apaw.domain.model.taskmanagement.TaskComment;
import es.upm.miw.apaw.domain.ports.out.taskmanagement.TaskCommentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TaskCommentAdapter implements TaskCommentGateway {

    private final TaskCommentRepository taskCommentRepository;
    private final TaskRepository taskRepository;

    @Override
    public TaskComment create(TaskComment taskComment) {
        return this.taskCommentRepository
                .save(new TaskCommentEntity(taskComment))
                .toDomain();
    }

    @Override
    public Optional<TaskComment> read(UUID id) {
        return this.taskCommentRepository.findById(id)
                .map(TaskCommentEntity::toDomain);
    }

    @Override
    public TaskComment update(TaskComment taskComment) {
        return this.taskCommentRepository
                .save(new TaskCommentEntity(taskComment))
                .toDomain();
    }

    @Override
    public void delete(UUID id) {
        this.taskCommentRepository.deleteById(id);
    }

    @Override
    public List<TaskComment> findAll() {
        return this.taskCommentRepository
                .findAll(Sort.by(
                        Sort.Order.asc("creationDate"),
                        Sort.Order.asc("id")
                ))
                .stream()
                .map(TaskCommentEntity::toDomain)
                .toList();
    }

    @Override
    public boolean isUsed(UUID id) {
        return this.taskRepository.existsByCommentsId(id);
    }
}
