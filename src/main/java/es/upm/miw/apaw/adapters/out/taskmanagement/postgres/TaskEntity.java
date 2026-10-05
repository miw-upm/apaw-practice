package es.upm.miw.apaw.adapters.out.taskmanagement.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.taskmanagement.Task;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TaskEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;

    private String description;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private Integer priority;

    @Column(nullable = false)
    private Boolean completion;

    private BigDecimal estimatedHours;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private List<TaskCommentEntity> comments;

    @Column(nullable = false)
    private UUID ownerId;

    public TaskEntity(Task task) {
        BeanUtils.copyProperties(task, this, "comments", "owner");

        this.comments = task.getComments().stream()
                .map(TaskCommentEntity::new)
                .toList();

        this.ownerId = task.getOwner().getId();
    }

    public Task toDomain() {
        Task task = new Task();

        BeanUtils.copyProperties(this, task, "comments", "ownerId");

        task.setComments(new ArrayList<>(this.comments.stream()
                .map(TaskCommentEntity::toDomain)
                .toList()));

        task.setOwner(
                UserSnapshot.builder()
                        .id(this.ownerId)
                        .build()
        );

        return task;
    }
}
