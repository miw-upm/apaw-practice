package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.legalprocedure.postgres.LegalTaskEntity;
import es.upm.miw.apaw.adapters.out.legalprocedure.postgres.LegalTaskRepository;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalTask;
import es.upm.miw.apaw.domain.models.legalprocedure.TaskStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(1)
@RequiredArgsConstructor
public class LegalTaskSeederForDev implements ApplicationRunner {
    public static final String PREFIX = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff";
    public static final UUID ID_0 = UUID.fromString(PREFIX + "0000");
    public static final LegalTask TASK_0 = LegalTask.builder()
            .id(ID_0)
            .title("Review documentation")
            .creatingDate(LocalDateTime.of(2025, 1, 10, 9, 0))
            .notes("Review the documents provided by the client")
            .taskStatus(TaskStatus.PENDING)
            .build();
    public static final UUID ID_1 = UUID.fromString(PREFIX + "0001");
    public static final LegalTask TASK_1 = LegalTask.builder()
            .id(ID_1)
            .title("Draft claim")
            .creatingDate(LocalDateTime.of(2025, 2, 10, 10, 0))
            .notes("Prepare the initial claim")
            .taskStatus(TaskStatus.IN_PROGRESS)
            .build();
    public static final UUID ID_2 = UUID.fromString(PREFIX + "0002");
    public static final LegalTask TASK_2 = LegalTask.builder()
            .id(ID_2)
            .title("Obtain expert report")
            .creatingDate(LocalDateTime.of(2025, 3, 10, 11, 0))
            .notes("Waiting for the expert report")
            .taskStatus(TaskStatus.BLOCKED)
            .build();
    public static final UUID ID_3 = UUID.fromString(PREFIX + "0003");
    public static final LegalTask TASK_3 = LegalTask.builder()
            .id(ID_3)
            .title("Submit evidence")
            .creatingDate(LocalDateTime.of(2025, 4, 10, 12, 0))
            .notes("Evidence submitted to the court")
            .taskStatus(TaskStatus.DONE)
            .build();
    public static final UUID ID_4 = UUID.fromString(PREFIX + "0004");
    public static final LegalTask TASK_4 = LegalTask.builder()
            .id(ID_4)
            .title("Prepare hearing")
            .creatingDate(LocalDateTime.of(2025, 5, 10, 9, 30))
            .taskStatus(TaskStatus.PENDING)
            .build();
    public static final UUID ID_5 = UUID.fromString(PREFIX + "0005");
    public static final LegalTask TASK_5 = LegalTask.builder()
            .id(ID_5)
            .title("Review settlement agreement")
            .creatingDate(LocalDateTime.of(2025, 6, 10, 10, 30))
            .taskStatus(TaskStatus.IN_PROGRESS)
            .build();

    private final LegalTaskRepository legalTaskRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        this.seed();
    }

    private void seed() {
        log.warn("------- Initial Load from JAVA -----------");
        List<LegalTaskEntity> legalTasks = List.of(TASK_0, TASK_1, TASK_2, TASK_3, TASK_4, TASK_5).stream()
                .filter(task -> !this.legalTaskRepository.existsById(task.getId()))
                .map(LegalTaskEntity::new)
                .toList();
        this.legalTaskRepository.saveAll(legalTasks);
        log.warn("        ------- legal tasks: {} added", legalTasks.size());
    }
}
