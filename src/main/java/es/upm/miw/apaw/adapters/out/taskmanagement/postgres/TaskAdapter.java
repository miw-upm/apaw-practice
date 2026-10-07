package es.upm.miw.apaw.adapters.out.taskmanagement.postgres;

import es.upm.miw.apaw.domain.model.taskmanagement.Task;
import es.upm.miw.apaw.domain.ports.out.taskmanagement.TaskGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
