package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.leases.postgres.AmendmentEntity;
import es.upm.miw.apaw.adapters.out.leases.postgres.AmendmentRepository;
import es.upm.miw.apaw.adapters.out.leases.postgres.LeaseEntity;
import es.upm.miw.apaw.adapters.out.leases.postgres.LeaseRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.leases.Amendment;
import es.upm.miw.apaw.domain.model.leases.AmendmentType;
import es.upm.miw.apaw.domain.model.leases.Lease;
import es.upm.miw.apaw.domain.model.leases.LeaseType;
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
@Order(3)
@RequiredArgsConstructor
public class LeaseSeederForDev implements ApplicationRunner {
    public static final String AMENDMENT_PREFIX = "dddddddd-eeee-ffff-aaaa-bbbbcccc";
    public static final UUID AMENDMENT_ID_0 = UUID.fromString(AMENDMENT_PREFIX + "0000");
    public static final Amendment AMENDMENT_0 = Amendment.builder()
            .id(AMENDMENT_ID_0)
            .amendmentNumber(1)
            .description("Annual rent update according to CPI")
            .effectiveDate(LocalDate.of(2025, 1, 1))
            .additionalAmount(new BigDecimal("25.00"))
            .approved(true)
            .amendmentType(AmendmentType.PRICE_CHANGE)
            .build();
    public static final UUID AMENDMENT_ID_1 = UUID.fromString(AMENDMENT_PREFIX + "0001");
    public static final Amendment AMENDMENT_1 = Amendment.builder()
            .id(AMENDMENT_ID_1)
            .amendmentNumber(2)
            .description("Lease extended for one additional year")
            .effectiveDate(LocalDate.of(2025, 6, 1))
            .approved(true)
            .amendmentType(AmendmentType.TERM_EXTENSION)
            .build();
    public static final UUID AMENDMENT_ID_2 = UUID.fromString(AMENDMENT_PREFIX + "0002");
    public static final Amendment AMENDMENT_2 = Amendment.builder()
            .id(AMENDMENT_ID_2)
            .amendmentNumber(1)
            .description("Storage room included in the lease")
            .effectiveDate(LocalDate.of(2025, 3, 15))
            .additionalAmount(new BigDecimal("60.00"))
            .approved(true)
            .amendmentType(AmendmentType.SCOPE_CHANGE)
            .build();
    public static final UUID AMENDMENT_ID_3 = UUID.fromString(AMENDMENT_PREFIX + "0003");
    public static final Amendment AMENDMENT_3 = Amendment.builder()
            .id(AMENDMENT_ID_3)
            .amendmentNumber(1)
            .description("Rent reduction requested by the tenant")
            .effectiveDate(LocalDate.of(2025, 9, 1))
            .additionalAmount(new BigDecimal("-40.00"))
            .approved(false)
            .amendmentType(AmendmentType.PRICE_CHANGE)
            .build();
    public static final UUID AMENDMENT_ID_4 = UUID.fromString(AMENDMENT_PREFIX + "0004");
    public static final Amendment AMENDMENT_4 = Amendment.builder()
            .id(AMENDMENT_ID_4)
            .amendmentNumber(3)
            .description("Early termination agreed by both parties")
            .effectiveDate(LocalDate.of(2025, 12, 31))
            .approved(false)
            .amendmentType(AmendmentType.TERMINATION)
            .build();

    private static final String USER_PREFIX = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff";
    private static final String LEASE_PREFIX = "eeeeeeee-ffff-aaaa-bbbb-ccccdddd";
    public static final UUID LEASE_ID_0 = UUID.fromString(LEASE_PREFIX + "0000");
    public static final Lease LEASE_0 = Lease.builder()
            .id(LEASE_ID_0)
            .leaseNumber("LSE-2024-0001")
            .cadastralReference("9872023VH5797S0001WX")
            .propertyAddress("Calle Mayor 10, 2A, Madrid")
            .startDate(LocalDate.of(2024, 1, 1))
            .endDate(LocalDate.of(2026, 12, 31))
            .monthlyRent(new BigDecimal("950.00"))
            .deposit(new BigDecimal("1900.00"))
            .active(true)
            .createdAt(LocalDateTime.of(2023, 12, 15, 10, 0))
            .leaseType(LeaseType.RESIDENTIAL)
            .amendments(List.of(AMENDMENT_0, AMENDMENT_1))
            .userSnapshot(user("0005"))
            .build();
    public static final UUID LEASE_ID_1 = UUID.fromString(LEASE_PREFIX + "0001");
    public static final Lease LEASE_1 = Lease.builder()
            .id(LEASE_ID_1)
            .leaseNumber("LSE-2024-0002")
            .propertyAddress("Avenida de America 45, Local 3, Madrid")
            .startDate(LocalDate.of(2024, 3, 1))
            .monthlyRent(new BigDecimal("2100.00"))
            .active(true)
            .createdAt(LocalDateTime.of(2024, 2, 20, 12, 30))
            .leaseType(LeaseType.COMMERCIAL)
            .amendments(List.of(AMENDMENT_2))
            .userSnapshot(user("0006"))
            .build();
    public static final UUID LEASE_ID_2 = UUID.fromString(LEASE_PREFIX + "0002");
    public static final Lease LEASE_2 = Lease.builder()
            .id(LEASE_ID_2)
            .leaseNumber("LSE-2025-0001")
            .cadastralReference("1234567VK4713N0001PQ")
            .propertyAddress("Paseo Maritimo 7, Benidorm")
            .startDate(LocalDate.of(2025, 7, 1))
            .endDate(LocalDate.of(2025, 8, 31))
            .monthlyRent(new BigDecimal("1300.00"))
            .deposit(new BigDecimal("1300.00"))
            .active(false)
            .createdAt(LocalDateTime.of(2025, 5, 2, 9, 15))
            .leaseType(LeaseType.SEASONAL)
            .amendments(List.of())
            .userSnapshot(user("0007"))
            .build();

    private final AmendmentRepository amendmentRepository;
    private final LeaseRepository leaseRepository;

    private static UserSnapshot user(String idSuffix) {
        return UserSnapshot.builder()
                .id(UUID.fromString(USER_PREFIX + idSuffix))
                .build();
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load from JAVA -----------");
        this.seedAmendments();
        this.seedLeases();
    }

    private void seedAmendments() {
        List<AmendmentEntity> amendments = List.of(AMENDMENT_0, AMENDMENT_1, AMENDMENT_2, AMENDMENT_3, AMENDMENT_4)
                .stream()
                .filter(amendment -> !this.amendmentRepository.existsById(amendment.getId()))
                .map(AmendmentEntity::new)
                .toList();
        this.amendmentRepository.saveAll(amendments);
        log.warn("        ------- amendments: {} added", amendments.size());
    }

    private void seedLeases() {
        List<LeaseEntity> leases = List.of(LEASE_0, LEASE_1, LEASE_2).stream()
                .filter(lease -> !this.leaseRepository.existsById(lease.getId()))
                .map(this::toEntity)
                .toList();
        this.leaseRepository.saveAll(leases);
        log.warn("        ------- leases: {} added", leases.size());
    }

    private LeaseEntity toEntity(Lease lease) {
        LeaseEntity entity = new LeaseEntity(lease);
        entity.setAmendments(lease.getAmendments().stream()
                .map(amendment -> this.amendmentRepository.getReferenceById(amendment.getId()))
                .collect(Collectors.toCollection(ArrayList::new)));
        return entity;
    }
}
