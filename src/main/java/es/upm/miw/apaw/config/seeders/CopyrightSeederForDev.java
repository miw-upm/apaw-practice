package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.copyright.postgres.ClaimEntity;
import es.upm.miw.apaw.adapters.out.copyright.postgres.ClaimRepository;
import es.upm.miw.apaw.adapters.out.copyright.postgres.CreativeWorkEntity;
import es.upm.miw.apaw.adapters.out.copyright.postgres.CreativeWorkRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.copyright.Claim;
import es.upm.miw.apaw.domain.model.copyright.CreativeWork;
import es.upm.miw.apaw.domain.model.copyright.FormatType;
import es.upm.miw.apaw.domain.model.copyright.TaskStatus;
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
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(1)
@RequiredArgsConstructor
public class CopyrightSeederForDev implements ApplicationRunner {

    public static final String PREFIX = "11111111-2222-3333-4444-55555555";
    
    // Obras (CreativeWorks)
    public static final UUID WORK_ID_0 = UUID.fromString(PREFIX + "0000");
    public static final CreativeWork WORK_0 = CreativeWork.builder()
            .id(WORK_ID_0)
            .registrationCode("RW-001")
            .title("El Quijote del Siglo XXI")
            .estimatedValuation(new BigDecimal("15000.00"))
            .registrationDate(LocalDate.of(2025, 1, 10))
            .authorPenName("CervantesModerno")
            .formatType(FormatType.LITERATURE)
            .author(user("0000", "600000100", "cliente0"))
            .build();

    public static final UUID WORK_ID_1 = UUID.fromString(PREFIX + "0001");
    public static final CreativeWork WORK_1 = CreativeWork.builder()
            .id(WORK_ID_1)
            .registrationCode("RW-002")
            .title("Sinfonía en Código Menor")
            .estimatedValuation(new BigDecimal("32000.50"))
            .registrationDate(LocalDate.of(2025, 2, 15))
            .authorPenName("BeethovenTech")
            .formatType(FormatType.MUSIC)
            .author(user("0001", "600000101", "cliente1"))
            .build();

    // Denuncias (Claims)
    public static final UUID CLAIM_ID_0 = UUID.fromString(PREFIX + "1000");
    public static final Claim CLAIM_0 = Claim.builder()
            .id(CLAIM_ID_0)
            .number("CLM-001")
            .filingDate(LocalDateTime.of(2025, 3, 1, 10, 0))
            .requestedCompensation(new BigDecimal("5000.00"))
            .urgent(true)
            .resolutionNotes("En proceso de revisión pericial.")
            .taskStatus(TaskStatus.CURRENT)
            .defendant(user("0002", "600000102", "cliente2"))
            .build();

    public static final UUID CLAIM_ID_1 = UUID.fromString(PREFIX + "1001");
    public static final Claim CLAIM_1 = Claim.builder()
            .id(CLAIM_ID_1)
            .number("CLM-002")
            .filingDate(LocalDateTime.of(2025, 4, 15, 12, 30))
            .requestedCompensation(new BigDecimal("1000.00"))
            .urgent(false)
            .taskStatus(TaskStatus.WITHDRAWN)
            .defendant(user("0003", "600000103", "cliente3"))
            .build();

    public static final UUID CLAIM_ID_2 = UUID.fromString(PREFIX + "1002");
    public static final Claim CLAIM_2 = Claim.builder()
            .id(CLAIM_ID_2)
            .number("CLM-003")
            .filingDate(LocalDateTime.of(2025, 5, 20, 9, 15))
            .requestedCompensation(new BigDecimal("12500.00"))
            .urgent(true)
            .resolutionNotes("Desestimada por falta de pruebas.")
            .taskStatus(TaskStatus.DEPRECATED)
            .defendant(user("0004", "600000104", "cliente4"))
            .build();

    private final CreativeWorkRepository creativeWorkRepository;
    private final ClaimRepository claimRepository;

    private static UserSnapshot user(String idSuffix, String mobile, String firstName) {
        return UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff" + idSuffix))
                .mobile(mobile)
                .firstName(firstName)
                .build();
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load from JAVA -----------");
        this.seedCreativeWorks();
        this.seedClaims();
    }

    private void seedCreativeWorks() {
        List<CreativeWorkEntity> works = List.of(WORK_0, WORK_1).stream()
                .filter(work -> !this.creativeWorkRepository.existsById(work.getId()))
                .map(CreativeWorkEntity::new)
                .toList();
        
        if (!works.isEmpty()) {
            this.creativeWorkRepository.saveAll(works);
            log.warn("        ------- creative works: {} added", works.size());
        }
    }

    private void seedClaims() {
        if (!this.claimRepository.existsById(CLAIM_0.getId())) {
            this.saveClaimWithWork(CLAIM_0, WORK_ID_0);
        }
        if (!this.claimRepository.existsById(CLAIM_1.getId())) {
            this.saveClaimWithWork(CLAIM_1, WORK_ID_0);
        }
        if (!this.claimRepository.existsById(CLAIM_2.getId())) {
            this.saveClaimWithWork(CLAIM_2, WORK_ID_1);
        }
    }

    private void saveClaimWithWork(Claim claim, UUID workId) {
        ClaimEntity entity = new ClaimEntity(claim);
        entity.setCreativeWork(this.creativeWorkRepository.getReferenceById(workId));
        this.claimRepository.save(entity);
        log.warn("        ------- claim: {} added", claim.getNumber());
    }
}
