package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.legalprocedure.postgres.LegalProcedureEntity;
import es.upm.miw.apaw.adapters.out.legalprocedure.postgres.LegalProcedureRepository;
import es.upm.miw.apaw.adapters.out.legalprocedure.postgres.LegalTaskEntity;
import es.upm.miw.apaw.adapters.out.legalprocedure.postgres.LegalTaskRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.legalprocedure.LegalProcedure;
import es.upm.miw.apaw.domain.model.legalprocedure.LegalTask;
import es.upm.miw.apaw.domain.model.legalprocedure.TaskStatus;
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
public class LegalProcedureSeederForDev implements ApplicationRunner {
    public static final String PREFIX = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff";
    public static final UUID ID_0 = UUID.fromString(PREFIX + "0000");
    public static final LegalTask TASK_0 = LegalTask.builder()
            .id(ID_0)
            .title("Review documentation")
            .creatingDate(LocalDateTime.of(2025, 1, 10, 9, 0))
            .notes("Review the documents provided by the client")
            .taskStatus(TaskStatus.CURRENT)
            .build();
    public static final UUID ID_1 = UUID.fromString(PREFIX + "0001");
    public static final LegalTask TASK_1 = LegalTask.builder()
            .id(ID_1)
            .title("Draft claim")
            .creatingDate(LocalDateTime.of(2025, 2, 10, 10, 0))
            .notes("Prepare the initial claim")
            .taskStatus(TaskStatus.CURRENT)
            .build();
    public static final UUID ID_2 = UUID.fromString(PREFIX + "0002");
    public static final LegalTask TASK_2 = LegalTask.builder()
            .id(ID_2)
            .title("Obtain expert report")
            .creatingDate(LocalDateTime.of(2025, 3, 10, 11, 0))
            .notes("Waiting for the expert report")
            .taskStatus(TaskStatus.DEPRECATED)
            .build();
    public static final UUID ID_3 = UUID.fromString(PREFIX + "0003");
    public static final LegalTask TASK_3 = LegalTask.builder()
            .id(ID_3)
            .title("Submit evidence")
            .creatingDate(LocalDateTime.of(2025, 4, 10, 12, 0))
            .notes("Evidence submitted to the court")
            .taskStatus(TaskStatus.WITHDRAWN)
            .build();
    public static final UUID ID_4 = UUID.fromString(PREFIX + "0004");
    public static final LegalTask TASK_4 = LegalTask.builder()
            .id(ID_4)
            .title("Prepare hearing")
            .creatingDate(LocalDateTime.of(2025, 5, 10, 9, 30))
            .taskStatus(TaskStatus.CURRENT)
            .build();
    public static final UUID ID_5 = UUID.fromString(PREFIX + "0005");
    public static final LegalTask TASK_5 = LegalTask.builder()
            .id(ID_5)
            .title("Review settlement agreement")
            .creatingDate(LocalDateTime.of(2025, 6, 10, 10, 30))
            .taskStatus(TaskStatus.CURRENT)
            .build();

    private static final String PROCEDURE_PREFIX = "bbbbbbbb-cccc-dddd-eeee-ffffffff";
    public static final UUID PROCEDURE_ID_0 = UUID.fromString(PROCEDURE_PREFIX + "0000");
    public static final LegalProcedure PROCEDURE_0 = LegalProcedure.builder()
            .id(PROCEDURE_ID_0)
            .title("Employment contract review")
            .startedDate(LocalDate.of(2025, 1, 15))
            .budget(new BigDecimal("850.00"))
            .budgetProposal("Fixed fee including the initial consultation")
            .vatIncluded(false)
            .legalTasks(List.of(TASK_0, TASK_1))
            .userSnapshot(user("0000", "600000100", "cliente0"))
            .build();
    public static final UUID PROCEDURE_ID_1 = UUID.fromString(PROCEDURE_PREFIX + "0001");
    public static final LegalProcedure PROCEDURE_1 = LegalProcedure.builder()
            .id(PROCEDURE_ID_1)
            .title("Property purchase claim")
            .startedDate(LocalDate.of(2025, 2, 3))
            .closingDate(LocalDate.of(2025, 6, 30))
            .budget(new BigDecimal("2400.00"))
            .budgetProposal("Initial provision of funds and success fee")
            .vatIncluded(false)
            .legalTasks(List.of(TASK_0, TASK_2, TASK_3))
            .userSnapshot(user("0001", "600000101", "cliente1"))
            .build();
    public static final UUID PROCEDURE_ID_2 = UUID.fromString(PROCEDURE_PREFIX + "0002");
    public static final LegalProcedure PROCEDURE_2 = LegalProcedure.builder()
            .id(PROCEDURE_ID_2)
            .title("Tax inspection appeal")
            .startedDate(LocalDate.of(2025, 3, 18))
            .budget(new BigDecimal("1750.00"))
            .budgetProposal("Fee divided into allegation and appeal phases")
            .vatIncluded(false)
            .legalTasks(List.of(TASK_0, TASK_4))
            .userSnapshot(user("0002", "600000102", "cliente2"))
            .build();
    public static final UUID PROCEDURE_ID_3 = UUID.fromString(PROCEDURE_PREFIX + "0003");
    public static final LegalProcedure PROCEDURE_3 = LegalProcedure.builder()
            .id(PROCEDURE_ID_3)
            .title("Commercial debt recovery")
            .startedDate(LocalDate.of(2025, 4, 7))
            .closingDate(LocalDate.of(2025, 9, 15))
            .budget(new BigDecimal("1200.00"))
            .budgetProposal("Fixed fee plus court representation expenses")
            .vatIncluded(false)
            .legalTasks(List.of(TASK_1, TASK_3, TASK_5))
            .userSnapshot(user("0003", "600000103", "cliente3"))
            .build();
    public static final UUID PROCEDURE_ID_4 = UUID.fromString(PROCEDURE_PREFIX + "0004");
    public static final LegalProcedure PROCEDURE_4 = LegalProcedure.builder()
            .id(PROCEDURE_ID_4)
            .title("Inheritance settlement")
            .startedDate(LocalDate.of(2025, 5, 22))
            .budget(new BigDecimal("3100.00"))
            .budgetProposal("Estimate subject to the number of inherited assets")
            .vatIncluded(false)
            .legalTasks(List.of(TASK_0, TASK_1, TASK_2, TASK_4))
            .userSnapshot(user("0004", "600000104", "cliente4"))
            .build();

    private final LegalTaskRepository legalTaskRepository;
    private final LegalProcedureRepository legalProcedureRepository;

    private static UserSnapshot user(String idSuffix, String mobile, String firstName) {
        return UserSnapshot.builder()
                .id(UUID.fromString(PREFIX + idSuffix))
                .mobile(mobile)
                .firstName(firstName)
                .build();
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load from JAVA -----------");
        this.seedLegalTasks();
        this.seedLegalProcedures();
    }

    private void seedLegalTasks() {
        List<LegalTaskEntity> legalTasks = List.of(TASK_0, TASK_1, TASK_2, TASK_3, TASK_4, TASK_5).stream()
                .filter(task -> !this.legalTaskRepository.existsById(task.getId()))
                .map(LegalTaskEntity::new)
                .toList();
        this.legalTaskRepository.saveAll(legalTasks);
        log.warn("        ------- legal tasks: {} added", legalTasks.size());
    }

    private void seedLegalProcedures() {
        List<LegalProcedureEntity> legalProcedures = List.of(
                        PROCEDURE_0, PROCEDURE_1, PROCEDURE_2, PROCEDURE_3, PROCEDURE_4).stream()
                .filter(procedure -> !this.legalProcedureRepository.existsById(procedure.getId()))
                .map(this::toEntity)
                .toList();
        this.legalProcedureRepository.saveAll(legalProcedures);
        log.warn("        ------- legal procedures: {} added", legalProcedures.size());
    }

    private LegalProcedureEntity toEntity(LegalProcedure legalProcedure) {
        LegalProcedureEntity entity = new LegalProcedureEntity(legalProcedure);
        entity.setLegalTasks(legalProcedure.getLegalTasks().stream()
                .map(task -> this.legalTaskRepository.getReferenceById(task.getId()))
                .collect(Collectors.toCollection(ArrayList::new)));
        return entity;
    }
}
