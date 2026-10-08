package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.taskmanagement.postgres.TaskCommentEntity;
import es.upm.miw.apaw.adapters.out.taskmanagement.postgres.TaskCommentRepository;
import es.upm.miw.apaw.adapters.out.taskmanagement.postgres.TaskEntity;
import es.upm.miw.apaw.adapters.out.taskmanagement.postgres.TaskRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.taskmanagement.CommentType;
import es.upm.miw.apaw.domain.model.taskmanagement.Task;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskComment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(1)
@RequiredArgsConstructor
public class TaskManagementSeederForDev implements ApplicationRunner {

    private static final String COMMENT_PREFIX = "cccccccc-dddd-eeee-ffff-11112222";

    private static final String TASK_PREFIX = "dddddddd-eeee-ffff-aaaa-33334444";

    private static final String USER_PREFIX = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff";

    public static final UUID COMMENT_ID_0 = UUID.fromString(COMMENT_PREFIX + "0000");
    public static final TaskComment COMMENT_0 = TaskComment.builder()
            .id(COMMENT_ID_0)
            .content("Initial requirements reviewed")
            .creationDate(LocalDateTime.of(2025, 7, 10, 9, 0))
            .edition(false)
            .attachment(false)
            .type(CommentType.GENERAL)
            .author(user(
                    "0003",
                    "cliente3",
                    "Fernández Torres",
                    "cliente3@example.com"
            ))
            .build();

    public static final UUID COMMENT_ID_1 = UUID.fromString(COMMENT_PREFIX + "0001");
    public static final TaskComment COMMENT_1 = TaskComment.builder()
            .id(COMMENT_ID_1)
            .content("Please prioritize this task")
            .creationDate(LocalDateTime.of(2025, 7, 11, 10, 30))
            .edition(true)
            .attachment(false)
            .type(CommentType.IMPORTANT)
            .author(user(
                    "0004",
                    "cliente4",
                    "Romero Navarro",
                    "cliente4@example.com"
            ))
            .build();

    public static final UUID COMMENT_ID_2 = UUID.fromString(COMMENT_PREFIX + "0002");
    public static final TaskComment COMMENT_2 = TaskComment.builder()
            .id(COMMENT_ID_2)
            .content("Internal implementation note")
            .creationDate(LocalDateTime.of(2025, 7, 12, 12, 0))
            .edition(false)
            .attachment(true)
            .type(CommentType.INTERNAL)
            .author(user(
                    "0005",
                    "cliente5",
                    "Moreno Castro",
                    "cliente5@example.com"
            ))
            .build();

    public static final UUID COMMENT_ID_3 = UUID.fromString(COMMENT_PREFIX + "0003");
    public static final TaskComment COMMENT_3 = TaskComment.builder()
            .id(COMMENT_ID_3)
            .content("Estimate confirmed")
            .creationDate(LocalDateTime.of(2025, 7, 13, 8, 45))
            .edition(false)
            .attachment(false)
            .type(CommentType.GENERAL)
            .author(user(
                    "0005",
                    "cliente5",
                    "Moreno Castro",
                    "cliente5@example.com"
            ))
            .build();

    public static final UUID TASK_ID_0 = UUID.fromString(TASK_PREFIX + "0000");
    public static final Task TASK_0 = Task.builder()
            .id(TASK_ID_0)
            .title("Prepare project proposal")
            .description("Prepare the first project proposal")
            .dueDate(LocalDate.of(2025, 8, 1))
            .priority(1)
            .completion(false)
            .estimatedHours(new BigDecimal("8.50"))
            .comments(List.of(COMMENT_0, COMMENT_1))
            .owner(user(
                    "0000",
                    "cliente0",
                    "García López",
                    "cliente0@example.com"
            ))
            .build();

    public static final UUID TASK_ID_1 = UUID.fromString(TASK_PREFIX + "0001");
    public static final Task TASK_1 = Task.builder()
            .id(TASK_ID_1)
            .title("Implement authentication module")
            .description("Implement authentication and authorization")
            .dueDate(LocalDate.of(2025, 8, 15))
            .priority(2)
            .completion(false)
            .estimatedHours(new BigDecimal("16.00"))
            .comments(List.of(COMMENT_2))
            .owner(user(
                    "0001",
                    "cliente1",
                    "Martínez Ruiz",
                    "cliente1@example.com"
            ))
            .build();

    public static final UUID TASK_ID_2 = UUID.fromString(TASK_PREFIX + "0002");
    public static final Task TASK_2 = Task.builder()
            .id(TASK_ID_2)
            .title("Review final documentation")
            .dueDate(LocalDate.of(2025, 9, 5))
            .priority(3)
            .completion(true)
            .comments(List.of(COMMENT_3))
            .owner(user(
                    "0002",
                    "cliente2",
                    "Sánchez Pérez",
                    "cliente2@example.com"

            ))
            .build();

    private final TaskCommentRepository taskCommentRepository;
    private final TaskRepository taskRepository;

    private static UserSnapshot user(String idSuffix, String firstName, String familyName, String email) {
        return UserSnapshot.builder()
                .id(UUID.fromString(USER_PREFIX + idSuffix))
                .firstName(firstName)
                .familyName(familyName)
                .email(email)
                .build();
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load from JAVA -----------");
        this.seedTaskComments();
        this.seedTasks();
    }

    private void seedTaskComments() {
        List<TaskCommentEntity> taskComments =
                List.of(COMMENT_0, COMMENT_1, COMMENT_2, COMMENT_3).stream()
                        .filter(comment -> !this.taskCommentRepository.existsById(comment.getId()))
                        .map(TaskCommentEntity::new)
                        .toList();

        this.taskCommentRepository.saveAll(taskComments);

        log.warn("        ------- task comments: {} added", taskComments.size());
    }

    private void seedTasks() {
        List<TaskEntity> tasks =
                List.of(TASK_0, TASK_1, TASK_2).stream()
                        .filter(task -> !this.taskRepository.existsById(task.getId()))
                        .map(this::toEntity)
                        .toList();

        this.taskRepository.saveAll(tasks);

        log.warn("        ------- tasks: {} added", tasks.size());
    }

    private TaskEntity toEntity(Task task) {
        TaskEntity entity = new TaskEntity(task);

        entity.setComments(task.getComments().stream()
                .map(comment -> this.taskCommentRepository.getReferenceById(comment.getId()))
                .collect(Collectors.toCollection(ArrayList::new))
        );

        return entity;
    }
}
