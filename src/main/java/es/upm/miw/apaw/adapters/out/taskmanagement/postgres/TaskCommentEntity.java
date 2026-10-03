package es.upm.miw.apaw.adapters.out.taskmanagement.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.taskmanagement.CommentType;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskComment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TaskCommentEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private Boolean edited;

    @Column(nullable = false)
    private Boolean hasAttachment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommentType type;

    @Column(nullable = false)
    private UUID userId;

    public TaskCommentEntity(TaskComment taskComment) {
        BeanUtils.copyProperties(taskComment, this, "author");
        this.userId = taskComment.getAuthor().getId();
    }

    public TaskComment toDomain() {
        TaskComment taskComment = new TaskComment();

        BeanUtils.copyProperties(this, taskComment, "userId");

        taskComment.setAuthor(
                UserSnapshot.builder()
                        .id(this.userId)
                        .build()
        );

        return taskComment;
    }
}
