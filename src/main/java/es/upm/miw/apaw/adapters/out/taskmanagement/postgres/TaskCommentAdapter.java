package es.upm.miw.apaw.adapters.out.taskmanagement.postgres;

import es.upm.miw.apaw.domain.ports.out.taskmanagement.TaskCommentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TaskCommentAdapter implements TaskCommentGateway {

    private final TaskCommentRepository taskCommentRepository;
}
