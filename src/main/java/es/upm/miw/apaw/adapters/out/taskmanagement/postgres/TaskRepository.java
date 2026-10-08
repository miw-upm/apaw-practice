package es.upm.miw.apaw.adapters.out.taskmanagement.postgres;

import es.upm.miw.apaw.domain.model.taskmanagement.CommentType;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskActivityReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskEntity, UUID>,
        JpaSpecificationExecutor<TaskEntity> {

    boolean existsByTitle(String title);

    boolean existsByCommentsId(UUID commentId);

    @Query("""
            select new es.upm.miw.apaw.domain.model.taskmanagement.TaskActivityReport(
                task.title,
                task.ownerId,
                count(comment.id),
                sum(case when comment.type = :importantType then 1 else 0 end),
                sum(case when comment.edition = true then 1 else 0 end),
                sum(case when comment.attachment = true then 1 else 0 end)
            )
            from TaskEntity task
            left join task.comments comment
            group by task.id, task.title, task.ownerId
            order by count(comment.id) desc, task.title asc
            """)
    List<TaskActivityReport> findActivityReport(@Param("importantType") CommentType importantType);
}
