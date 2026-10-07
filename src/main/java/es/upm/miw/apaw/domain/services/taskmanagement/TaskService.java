package es.upm.miw.apaw.domain.services.taskmanagement;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.taskmanagement.CreationTask;
import es.upm.miw.apaw.domain.model.taskmanagement.Task;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskComment;
import es.upm.miw.apaw.domain.ports.out.taskmanagement.TaskCommentGateway;
import es.upm.miw.apaw.domain.ports.out.taskmanagement.TaskGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskGateway taskGateway;
    private final TaskCommentGateway taskCommentGateway;
    private final UserFinder userFinder;

    public Task create(CreationTask creation) {
        if (this.taskGateway.existsByTitle(creation.getTitle())) {
            throw new ConflictException("Task title already exists: " + creation.getTitle());
        }

        Task task = new Task();
        BeanUtils.copyProperties(creation, task);

        task.setComments(creation.getTaskCommentIds().stream()
                .map(this::readTaskComment)
                .toList());

        task.setOwner(this.userFinder.read(creation.getOwnerId()));

        task.doDefault();

        return this.taskGateway.create(task);
    }

    private TaskComment readTaskComment(UUID id) {
        return this.taskCommentGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Task comment id not found: " + id));
    }
}