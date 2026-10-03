package es.upm.miw.apaw.adapters.out.taskmanagement.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskCommentRepository extends JpaRepository<TaskCommentEntity, UUID> {
}
