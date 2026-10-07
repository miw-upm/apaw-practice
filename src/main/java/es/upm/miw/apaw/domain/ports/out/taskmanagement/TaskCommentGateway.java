package es.upm.miw.apaw.domain.ports.out.taskmanagement;

import es.upm.miw.apaw.domain.model.taskmanagement.TaskComment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskCommentGateway {

    TaskComment create(TaskComment taskComment);

    Optional<TaskComment> read(UUID id);

    TaskComment update(TaskComment taskComment);

    void delete(UUID id);

    List<TaskComment> findAll();

    boolean isReferenced(UUID id);
}
