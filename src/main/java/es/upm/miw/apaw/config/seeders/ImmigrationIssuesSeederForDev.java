package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.immigrationissues.postgres.ImmigrationIssueEntity;
import es.upm.miw.apaw.adapters.out.immigrationissues.postgres.ImmigrationIssueRepository;
import es.upm.miw.apaw.adapters.out.immigrationissues.postgres.LawBasisEntity;
import es.upm.miw.apaw.adapters.out.immigrationissues.postgres.LawBasisRepository;
import es.upm.miw.apaw.domain.model.immigrationissues.ImmigrationIssue;
import es.upm.miw.apaw.domain.model.immigrationissues.LawBasis;
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
@Order(5)
@RequiredArgsConstructor
public class ImmigrationIssuesSeederForDev implements ApplicationRunner {

    public static final String LAW_BASIS_PREFIX = "eeeeeeee-ffff-1111-2222-33334444";

    public static final UUID ID_0 = UUID.fromString(LAW_BASIS_PREFIX + "0000");
    public static final LawBasis LAW_BASIS_0 = LawBasis.builder()
            .id(ID_0)
            .lawCode("ES-LB-001")
            .lawName("Ley Orgánica 4/2000")
            .articleNumber(1)
            .publishedOn(LocalDate.of(2000, 12, 22))
            .active(true)
            .build();

    public static final UUID ID_1 = UUID.fromString(LAW_BASIS_PREFIX + "0001");
    public static final LawBasis LAW_BASIS_1 = LawBasis.builder()
            .id(ID_1)
            .lawCode("ES-LB-002")
            .lawName("Ley Orgánica 4/2000")
            .articleNumber(5)
            .publishedOn(LocalDate.of(2000, 12, 22))
            .active(true)
            .build();

    public static final UUID ID_2 = UUID.fromString(LAW_BASIS_PREFIX + "0002");
    public static final LawBasis LAW_BASIS_2 = LawBasis.builder()
            .id(ID_2)
            .lawCode("ES-LB-003")
            .lawName("Ley 39/2015")
            .articleNumber(9)
            .publishedOn(LocalDate.of(2015, 10, 2))
            .active(false)
            .build();

    public static final UUID ID_3 = UUID.fromString(LAW_BASIS_PREFIX + "0003");
    public static final LawBasis LAW_BASIS_3 = LawBasis.builder()
            .id(ID_3)
            .lawCode("ES-LB-004")
            .lawName("Ley 12/1989")
            .articleNumber(3)
            .publishedOn(LocalDate.of(1989, 7, 26))
            .active(true)
            .build();

    public static final UUID ID_4 = UUID.fromString(LAW_BASIS_PREFIX + "0004");
    public static final LawBasis LAW_BASIS_4 = LawBasis.builder()
            .id(ID_4)
            .lawCode("ES-LB-005")
            .lawName("Reglamento CE 139/2004")
            .articleNumber(21)
            .publishedOn(LocalDate.of(2004, 2, 11))
            .active(true)
            .build();

    public static final UUID ID_5 = UUID.fromString(LAW_BASIS_PREFIX + "0005");
    public static final LawBasis LAW_BASIS_5 = LawBasis.builder()
            .id(ID_5)
            .lawCode("ES-LB-006")
            .lawName("Ley Orgánica 15/1999")
            .articleNumber(16)
            .publishedOn(LocalDate.of(1999, 12, 13))
            .active(false)
            .build();

    private static final String ISSUE_PREFIX = "eeeeeeee-ffff-1111-2222-55556666";
    private static final String USER_PREFIX = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff";

    public static final UUID ISSUE_ID_0 = UUID.fromString(ISSUE_PREFIX + "0000");
    public static final ImmigrationIssue ISSUE_0 = ImmigrationIssue.builder()
            .id(ISSUE_ID_0)
            .subject("Renovacion de permiso de trabajo")
            .clientNationality("Colombia")
            .clientImmigrationStatus("Permiso en vigor")
            .openedAt(LocalDateTime.of(2025, 2, 3, 10, 0))
            .responseDueDate(LocalDate.of(2025, 5, 3))
            .estimatedCost(new BigDecimal("450.00"))
            .lawBases(List.of(LAW_BASIS_0, LAW_BASIS_1))
            .build();

    public static final UUID ISSUE_ID_1 = UUID.fromString(ISSUE_PREFIX + "0001");
    public static final ImmigrationIssue ISSUE_1 = ImmigrationIssue.builder()
            .id(ISSUE_ID_1)
            .subject("Reagrupacion familiar")
            .clientNationality("Marruecos")
            .openedAt(LocalDateTime.of(2025, 3, 18, 9, 30))
            .responseDueDate(LocalDate.of(2025, 6, 18))
            .estimatedCost(new BigDecimal("620.50"))
            .lawBases(List.of(LAW_BASIS_2, LAW_BASIS_3))
            .build();

    public static final UUID ISSUE_ID_2 = UUID.fromString(ISSUE_PREFIX + "0002");
    public static final ImmigrationIssue ISSUE_2 = ImmigrationIssue.builder()
            .id(ISSUE_ID_2)
            .subject("Arraigo social")
            .clientNationality("Peru")
            .clientImmigrationStatus("Solicitud presentada")
            .openedAt(LocalDateTime.of(2025, 4, 22, 16, 45))
            .responseDueDate(LocalDate.of(2025, 10, 22))
            .estimatedCost(new BigDecimal("300.00"))
            .lawBases(List.of(LAW_BASIS_4))
            .build();

    private static final List<SeededIssue> SEEDED_ISSUES = List.of(
            new SeededIssue(ISSUE_0, UUID.fromString(USER_PREFIX + "0000")),
            new SeededIssue(ISSUE_1, UUID.fromString(USER_PREFIX + "0001")),
            new SeededIssue(ISSUE_2, UUID.fromString(USER_PREFIX + "0002")));

    private final LawBasisRepository lawBasisRepository;
    private final ImmigrationIssueRepository immigrationIssueRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load from JAVA -----------");
        this.seedLawBases();
        this.seedImmigrationIssues();
    }

    private void seedLawBases() {
        List<LawBasisEntity> lawBases = List.of(
                        LAW_BASIS_0, LAW_BASIS_1, LAW_BASIS_2,
                        LAW_BASIS_3, LAW_BASIS_4, LAW_BASIS_5).stream()
                .filter(lawBasis -> !this.lawBasisRepository.existsById(lawBasis.getId()))
                .map(LawBasisEntity::new)
                .toList();
        this.lawBasisRepository.saveAll(lawBases);
        log.warn("        ------- law bases: {} added", lawBases.size());
    }

    private void seedImmigrationIssues() {
        List<ImmigrationIssueEntity> issues = SEEDED_ISSUES.stream()
                .filter(seeded -> !this.immigrationIssueRepository.existsById(seeded.issue().getId()))
                .map(this::toEntity)
                .toList();
        this.immigrationIssueRepository.saveAll(issues);
        log.warn("        ------- immigration issues: {} added", issues.size());
    }

    private ImmigrationIssueEntity toEntity(SeededIssue seeded) {
        ImmigrationIssue issue = seeded.issue();
        ImmigrationIssueEntity entity = new ImmigrationIssueEntity();
        entity.setId(issue.getId());
        entity.setSubject(issue.getSubject());
        entity.setClientNationality(issue.getClientNationality());
        entity.setClientImmigrationStatus(issue.getClientImmigrationStatus());
        entity.setOpenedAt(issue.getOpenedAt());
        entity.setResponseDueDate(issue.getResponseDueDate());
        entity.setEstimatedCost(issue.getEstimatedCost());
        entity.setUserId(seeded.userId());
        entity.setLawBases(issue.getLawBases().stream()
                .map(lawBasis -> this.lawBasisRepository.getReferenceById(lawBasis.getId()))
                .collect(Collectors.toCollection(ArrayList::new)));
        return entity;
    }

    private record SeededIssue(ImmigrationIssue issue, UUID userId) {
    }
}