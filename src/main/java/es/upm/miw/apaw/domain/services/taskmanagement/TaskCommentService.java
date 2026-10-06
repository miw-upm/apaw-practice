package es.upm.miw.apaw.domain.services.taskmanagement;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.taskmanagement.CommentType;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskComment;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskCommentUpdate;
import es.upm.miw.apaw.domain.ports.out.taskmanagement.TaskCommentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public TaskComment update(UUID id, TaskCommentUpdate update) {
        TaskComment taskComment = this.read(id);

        if (update.content() != null) {
            taskComment.setContent(update.content());
        }

        if (update.edition() != null) {
            taskComment.setEdition(update.edition());
        }

        if (update.attachment() != null) {
            taskComment.setAttachment(update.attachment());
        }

        if (update.type() != null) {
            taskComment.setType(update.type());
        }

        if (update.author() != null) {
            taskComment.setAuthor(update.author());
        }

        return this.taskCommentGateway.update(taskComment);
    }
}
