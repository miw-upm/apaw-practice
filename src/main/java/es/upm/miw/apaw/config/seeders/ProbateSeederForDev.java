package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.probate.postgres.EstateEntity;
import es.upm.miw.apaw.adapters.out.probate.postgres.EstateRepository;
import es.upm.miw.apaw.adapters.out.probate.postgres.HeirEntity;
import es.upm.miw.apaw.adapters.out.probate.postgres.HeirRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.probate.Estate;
import es.upm.miw.apaw.domain.model.probate.Heir;
import es.upm.miw.apaw.domain.model.probate.HeirStatus;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(2)
@RequiredArgsConstructor
public class ProbateSeederForDev implements ApplicationRunner {
    public static final String HEIR_PREFIX = "c0c0c0c0-d0d0-e0e0-f0f0-a0a0a0a0";
    public static final UUID HEIR_ID_0 = UUID.fromString(HEIR_PREFIX + "0000");
    public static final Heir HEIR_0 = Heir.builder()
            .id(HEIR_ID_0)
            .fullName("John Smith")
            .nationalId("12345678A")
            .birthDate(LocalDate.of(1970, 5, 10))
            .sharePercentage(new BigDecimal("50.00"))
            .heirStatus(HeirStatus.PENDING)
            .contactEmail("john.smith@example.com")
            .build();
    public static final UUID HEIR_ID_1 = UUID.fromString(HEIR_PREFIX + "0001");
    public static final Heir HEIR_1 = Heir.builder()
            .id(HEIR_ID_1)
            .fullName("Mary Smith")
            .nationalId("87654321B")
            .birthDate(LocalDate.of(1975, 8, 22))
            .sharePercentage(new BigDecimal("50.00"))
            .heirStatus(HeirStatus.ACCEPTED)
            .build();
    public static final UUID HEIR_ID_2 = UUID.fromString(HEIR_PREFIX + "0002");
    public static final Heir HEIR_2 = Heir.builder()
            .id(HEIR_ID_2)
            .fullName("Peter Jones")
            .nationalId("11223344C")
            .birthDate(LocalDate.of(1980, 3, 3))
            .sharePercentage(new BigDecimal("100.00"))
            .heirStatus(HeirStatus.NOTIFIED)
            .contactEmail("peter.jones@example.com")
            .build();

    private static final String USER_PREFIX = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff";
    public static final String ESTATE_PREFIX = "d0d0d0d0-e0e0-f0f0-a0a0-b0b0b0b0";
    public static final UUID ESTATE_ID_0 = UUID.fromString(ESTATE_PREFIX + "0000");
    public static final Estate ESTATE_0 = Estate.builder()
            .id(ESTATE_ID_0)
            .fileNumber("EXP-2025-001")
            .openedDate(LocalDate.of(2025, 1, 15))
            .deceasedName("Robert Smith")
            .netValue(new BigDecimal("250000.00"))
            .lastWill(true)
            .heirs(List.of(HEIR_0, HEIR_1))
            .userSnapshot(user("0000"))
            .build();
    public static final UUID ESTATE_ID_1 = UUID.fromString(ESTATE_PREFIX + "0001");
    public static final Estate ESTATE_1 = Estate.builder()
            .id(ESTATE_ID_1)
            .fileNumber("EXP-2025-002")
            .openedDate(LocalDate.of(2025, 3, 20))
            .deceasedName("Alice Jones")
            .netValue(new BigDecimal("120000.00"))
            .lastWill(false)
            .closingDate(LocalDate.of(2025, 9, 30))
            .heirs(List.of(HEIR_2))
            .userSnapshot(user("0001"))
            .build();

    private final HeirRepository heirRepository;
    private final EstateRepository estateRepository;

    private static UserSnapshot user(String idSuffix) {
        return UserSnapshot.builder()
                .id(UUID.fromString(USER_PREFIX + idSuffix))
                .build();
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load Probate from JAVA -----------");
        this.seedHeirs();
        this.seedEstates();
    }

    private void seedHeirs() {
        List<HeirEntity> heirs = List.of(HEIR_0, HEIR_1, HEIR_2).stream()
                .filter(heir -> !this.heirRepository.existsById(heir.getId()))
                .map(HeirEntity::new)
                .toList();
        this.heirRepository.saveAll(heirs);
        log.warn("        ------- heirs: {} added", heirs.size());
    }

    private void seedEstates() {
        List<EstateEntity> estates = List.of(ESTATE_0, ESTATE_1).stream()
                .filter(estate -> !this.estateRepository.existsById(estate.getId()))
                .map(this::toEntity)
                .toList();
        this.estateRepository.saveAll(estates);
        log.warn("        ------- estates: {} added", estates.size());
    }

    private EstateEntity toEntity(Estate estate) {
        EstateEntity entity = new EstateEntity(estate);
        entity.setHeirs(estate.getHeirs().stream()
                .map(heir -> this.heirRepository.getReferenceById(heir.getId()))
                .collect(Collectors.toCollection(ArrayList::new)));
        return entity;
    }
}
