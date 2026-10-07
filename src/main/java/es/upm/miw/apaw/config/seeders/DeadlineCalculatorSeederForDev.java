package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres.DeadlineEntity;
import es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres.DeadlineRepository;
import es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres.NonWorkingDayEntity;
import es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres.NonWorkingDayRepository;
import es.upm.miw.apaw.domain.model.deadlinecalculator.DayCountType;
import es.upm.miw.apaw.domain.model.deadlinecalculator.DeadlineStatus;
import es.upm.miw.apaw.domain.model.deadlinecalculator.NonWorkingDay;
import es.upm.miw.apaw.domain.model.deadlinecalculator.ScopeLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(1)
@RequiredArgsConstructor
public class DeadlineCalculatorSeederForDev implements ApplicationRunner {
    private static final String PREFIX = "dddddddd-1111-2222-3333-44445555";
    private static final String MADRID = "Madrid";
    private static final String CATALUNA = "Cataluña";

    public static final UUID ID_0 = UUID.fromString(PREFIX + "0000");
    public static final NonWorkingDay NON_WORKING_DAY_0 = NonWorkingDay.builder()
            .id(ID_0)
            .date(LocalDate.of(2026, 1, 1))
            .description("Año Nuevo")
            .scopeLevel(ScopeLevel.NATIONAL)
            .recurring(true)
            .build();
    public static final UUID ID_1 = UUID.fromString(PREFIX + "0001");
    public static final NonWorkingDay NON_WORKING_DAY_1 = NonWorkingDay.builder()
            .id(ID_1)
            .date(LocalDate.of(2026, 10, 12))
            .description("Fiesta Nacional de España")
            .scopeLevel(ScopeLevel.NATIONAL)
            .recurring(true)
            .build();
    public static final UUID ID_2 = UUID.fromString(PREFIX + "0002");
    public static final NonWorkingDay NON_WORKING_DAY_2 = NonWorkingDay.builder()
            .id(ID_2)
            .date(LocalDate.of(2026, 12, 25))
            .description("Navidad")
            .scopeLevel(ScopeLevel.NATIONAL)
            .recurring(true)
            .build();
    public static final UUID ID_3 = UUID.fromString(PREFIX + "0003");
    public static final NonWorkingDay NON_WORKING_DAY_3 = NonWorkingDay.builder()
            .id(ID_3)
            .date(LocalDate.of(2026, 5, 2))
            .description("Día de la Comunidad de Madrid")
            .scopeLevel(ScopeLevel.REGIONAL)
            .region(MADRID)
            .recurring(true)
            .build();
    public static final UUID ID_4 = UUID.fromString(PREFIX + "0004");
    public static final NonWorkingDay NON_WORKING_DAY_4 = NonWorkingDay.builder()
            .id(ID_4)
            .date(LocalDate.of(2026, 9, 11))
            .description("Diada Nacional de Catalunya")
            .scopeLevel(ScopeLevel.REGIONAL)
            .region(CATALUNA)
            .recurring(true)
            .build();
    public static final UUID ID_5 = UUID.fromString(PREFIX + "0005");
    public static final NonWorkingDay NON_WORKING_DAY_5 = NonWorkingDay.builder()
            .id(ID_5)
            .date(LocalDate.of(2026, 5, 15))
            .description("San Isidro")
            .scopeLevel(ScopeLevel.LOCAL)
            .region(MADRID)
            .city(MADRID)
            .recurring(true)
            .build();
    public static final UUID ID_6 = UUID.fromString(PREFIX + "0006");
    public static final NonWorkingDay NON_WORKING_DAY_6 = NonWorkingDay.builder()
            .id(ID_6)
            .date(LocalDate.of(2026, 5, 15))
            .description("San Isidro")
            .scopeLevel(ScopeLevel.LOCAL)
            .region(MADRID)
            .city("Alcobendas")
            .recurring(true)
            .build();
    public static final UUID ID_7 = UUID.fromString(PREFIX + "0007");
    public static final NonWorkingDay NON_WORKING_DAY_7 = NonWorkingDay.builder()
            .id(ID_7)
            .date(LocalDate.of(2026, 9, 24))
            .description("La Mercè")
            .scopeLevel(ScopeLevel.LOCAL)
            .region(CATALUNA)
            .city("Barcelona")
            .recurring(true)
            .build();
    public static final UUID ID_8 = UUID.fromString(PREFIX + "0008");
    public static final NonWorkingDay NON_WORKING_DAY_8 = NonWorkingDay.builder()
            .id(ID_8)
            .date(LocalDate.of(2026, 11, 9))
            .description("Nuestra Señora de la Almudena")
            .scopeLevel(ScopeLevel.LOCAL)
            .region(MADRID)
            .city(MADRID)
            .recurring(true)
            .build();
    public static final UUID ID_9 = UUID.fromString(PREFIX + "0009");
    public static final NonWorkingDay NON_WORKING_DAY_9 = NonWorkingDay.builder()
            .id(ID_9)
            .date(LocalDate.of(2026, 1, 24))
            .description("Nuestra Señora de la Paz")
            .scopeLevel(ScopeLevel.LOCAL)
            .region(MADRID)
            .city("Alcobendas")
            .recurring(false)
            .build();

    /**
     * RESERVADO: referenciado por DEADLINE_ID. Ningún test debe modificarlo ni borrarlo.
     * Es el que permite comprobar los 409 de referencia del PUT y del DELETE.
     */
    public static final UUID ID_10 = UUID.fromString(PREFIX + "0010");
    public static final NonWorkingDay NON_WORKING_DAY_10 = NonWorkingDay.builder()
            .id(ID_10)
            .date(LocalDate.of(2026, 6, 15))
            .description("Festividad local")
            .scopeLevel(ScopeLevel.LOCAL)
            .region(MADRID)
            .city("Coslada")
            .recurring(false)
            .build();

    public static final UUID DEADLINE_ID = UUID.fromString(PREFIX + "1000");
    public static final UUID USER_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000");

    private final NonWorkingDayRepository nonWorkingDayRepository;
    private final DeadlineRepository deadlineRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load Deadline Calculator -----------");
        this.seedNonWorkingDays();
        this.seedDeadline();
    }

    private void seedNonWorkingDays() {
        List<NonWorkingDayEntity> nonWorkingDays = List.of(
                        NON_WORKING_DAY_0, NON_WORKING_DAY_1, NON_WORKING_DAY_2, NON_WORKING_DAY_3,
                        NON_WORKING_DAY_4, NON_WORKING_DAY_5, NON_WORKING_DAY_6, NON_WORKING_DAY_7,
                        NON_WORKING_DAY_8, NON_WORKING_DAY_9, NON_WORKING_DAY_10).stream()
                .filter(nonWorkingDay -> !this.nonWorkingDayRepository.existsById(nonWorkingDay.getId()))
                .map(NonWorkingDayEntity::new)
                .toList();
        this.nonWorkingDayRepository.saveAll(nonWorkingDays);
        log.warn("        ------- non working days: {} added", nonWorkingDays.size());
    }

    private void seedDeadline() {
        if (this.deadlineRepository.existsById(DEADLINE_ID)) {
            return;
        }
        NonWorkingDayEntity referenced = this.nonWorkingDayRepository.findById(ID_10).orElseThrow();
        this.deadlineRepository.save(DeadlineEntity.builder()
                .id(DEADLINE_ID)
                .title("Contestación demanda - Coslada")
                .courtFileNumber("123/2026")
                .notificationDate(LocalDate.of(2026, 6, 10))
                .days(10)
                .dayCountType(DayCountType.WORKING)
                .region(MADRID)
                .city("Coslada")
                .status(DeadlineStatus.PENDING)
                .createdAt(LocalDateTime.of(2026, 6, 10, 9, 0))
                .dueDate(LocalDate.of(2026, 6, 25))
                .nonWorkingDays(List.of(referenced))
                .userId(USER_ID)
                .build());
        log.warn("        ------- deadlines: 1 added");
    }
}
