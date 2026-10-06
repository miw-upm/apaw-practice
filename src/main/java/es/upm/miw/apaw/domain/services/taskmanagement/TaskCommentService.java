package es.upm.miw.apaw.domain.services.taskmanagement;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.taskmanagement.CommentType;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskComment;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskCommentEditionAndTypeUpdate;
import es.upm.miw.apaw.domain.ports.out.taskmanagement.TaskCommentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.upm.miw.apaw.domain.exceptions.BadRequestException;

import java.util.HashSet;
import java.util.Set;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskCommentService {

    private final TaskCommentGateway taskCommentGateway;

    public TaskComment create(TaskComment taskComment) {
        taskComment.doDefault();
        return this.taskCommentGateway.create(taskComment);
    }

    public TaskComment read(UUID id) {
        return this.taskCommentGateway.read(id)
                .orElseThrow(() ->
                        new NotFoundException("TaskComment not found: " + id)
                );
    }

    public TaskComment update(UUID id, TaskComment taskComment) {
        TaskComment storedTaskComment = this.read(id);
        if (taskComment.getEdition() == null) {
            taskComment.setEdition(false);
        }
        if (taskComment.getAttachment() == null) {
            taskComment.setAttachment(false);
        }
        if (taskComment.getType() == null) {
            taskComment.setType(CommentType.GENERAL);
        }
        storedTaskComment.setContent(taskComment.getContent());
        storedTaskComment.setEdition(taskComment.getEdition());
        storedTaskComment.setAttachment(taskComment.getAttachment());
        storedTaskComment.setType(taskComment.getType());
        storedTaskComment.setAuthor(taskComment.getAuthor());
        return this.taskCommentGateway.update(storedTaskComment);
    }

    public void delete(UUID id) {
        if (this.taskCommentGateway.isReferenced(id)) {
            throw new ConflictException(
                    "Task comment is referenced by a task: " + id
            );
        }
        this.taskCommentGateway.delete(id);
    }

    public List<TaskComment> findAll() {
        return this.taskCommentGateway.findAll();
    }

    @Transactional
    public void updateEditionAndType(List<TaskCommentEditionAndTypeUpdate> updates) {
        this.assertUniqueIds(updates);

        List<TaskComment> taskComments = updates.stream()
                .map(update -> {
                    TaskComment taskComment = this.read(update.id());
                    taskComment.setEdition(update.edition());
                    taskComment.setType(update.type());
                    return taskComment;
                })
                .toList();

        taskComments.forEach(this.taskCommentGateway::update);
    }

    private void assertUniqueIds(List<TaskCommentEditionAndTypeUpdate> updates) {
        Set<UUID> ids = new HashSet<>();
        for (TaskCommentEditionAndTypeUpdate update : updates) {
            if (!ids.add(update.id())) {
                throw new BadRequestException(
                        "Repeated task comment id: " + update.id()
                );
            }
        }
    }
}
