package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres.DeadlineEntity;
import es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres.DeadlineRepository;
import es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres.NonWorkingDayEntity;
import es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres.NonWorkingDayRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.deadlinecalculator.DayCountType;
import es.upm.miw.apaw.domain.model.deadlinecalculator.Deadline;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
     * RESERVADO: referenciado por DEADLINE_0. Ningún test debe modificarlo ni borrarlo.
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

    public static final UUID ID_11 = UUID.fromString(PREFIX + "0011");
    public static final NonWorkingDay NON_WORKING_DAY_11 = NonWorkingDay.builder()
            .id(ID_11)
            .date(LocalDate.of(2020, 5, 15))
            .description("San Isidro")
            .scopeLevel(ScopeLevel.LOCAL)
            .region(MADRID)
            .city(MADRID)
            .recurring(true)
            .build();
    public static final UUID ID_12 = UUID.fromString(PREFIX + "0012");
    public static final NonWorkingDay NON_WORKING_DAY_12 = NonWorkingDay.builder()
            .id(ID_12)
            .date(LocalDate.of(2020, 9, 11))
            .description("Diada Nacional de Catalunya")
            .scopeLevel(ScopeLevel.REGIONAL)
            .region(CATALUNA)
            .recurring(true)
            .build();

    private static final String USER_PREFIX = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff";
    public static final UUID USER_ID_0 = UUID.fromString(USER_PREFIX + "0000");
    public static final UUID USER_ID_1 = UUID.fromString(USER_PREFIX + "0001");
    public static final UUID USER_ID_2 = UUID.fromString(USER_PREFIX + "0002");
    private static final UserSnapshot USER_0 = user(USER_ID_0, "600000100", "cliente0");
    private static final UserSnapshot USER_1 = user(USER_ID_1, "600000101", "cliente1");
    private static final UserSnapshot USER_2 = user(USER_ID_2, "600000102", "cliente2");

    /**
     * RESERVADO: referencia al festivo ID_10. La fase 6 depende de que ese festivo esté en uso.
     */
    public static final UUID DEADLINE_ID_0 = UUID.fromString(PREFIX + "1000");
    public static final Deadline DEADLINE_0 = Deadline.builder()
            .id(DEADLINE_ID_0)
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
            .nonWorkingDays(List.of(NON_WORKING_DAY_10))
            .userSnapshot(USER_0)
            .build();

    public static final UUID DEADLINE_ID_1 = UUID.fromString(PREFIX + "1001");
    public static final Deadline DEADLINE_1 = Deadline.builder()
            .id(DEADLINE_ID_1)
            .title("Recurso de reposición - Madrid")
            .courtFileNumber("201/2020")
            .notificationDate(LocalDate.of(2020, 5, 12))
            .days(5)
            .dayCountType(DayCountType.WORKING)
            .region(MADRID)
            .city(MADRID)
            .status(DeadlineStatus.PENDING)
            .createdAt(LocalDateTime.of(2020, 5, 12, 9, 0))
            .dueDate(LocalDate.of(2020, 5, 20))
            .nonWorkingDays(List.of(NON_WORKING_DAY_11))
            .userSnapshot(USER_0)
            .build();

    public static final UUID DEADLINE_ID_2 = UUID.fromString(PREFIX + "1002");
    public static final Deadline DEADLINE_2 = Deadline.builder()
            .id(DEADLINE_ID_2)
            .title("Alegaciones previas - Madrid")
            .notificationDate(LocalDate.of(2020, 2, 3))
            .days(3)
            .dayCountType(DayCountType.WORKING)
            .region(MADRID)
            .city(MADRID)
            .status(DeadlineStatus.PENDING)
            .createdAt(LocalDateTime.of(2020, 2, 3, 9, 0))
            .dueDate(LocalDate.of(2020, 2, 6))
            .nonWorkingDays(List.of())
            .userSnapshot(USER_0)
            .build();

    /**
     * Días naturales con San Isidro de 2020 dentro del tramo: la relación queda vacía
     * porque el cómputo por días naturales no consulta el calendario de festivos.
     */
    public static final UUID DEADLINE_ID_3 = UUID.fromString(PREFIX + "1003");
    public static final Deadline DEADLINE_3 = Deadline.builder()
            .id(DEADLINE_ID_3)
            .title("Comparecencia penal - Madrid")
            .courtFileNumber("202/2020")
            .notificationDate(LocalDate.of(2020, 5, 11))
            .days(10)
            .dayCountType(DayCountType.CALENDAR)
            .region(MADRID)
            .city(MADRID)
            .status(DeadlineStatus.PENDING)
            .createdAt(LocalDateTime.of(2020, 5, 11, 9, 0))
            .dueDate(LocalDate.of(2020, 5, 21))
            .nonWorkingDays(List.of())
            .userSnapshot(USER_0)
            .build();

    public static final UUID DEADLINE_ID_4 = UUID.fromString(PREFIX + "1004");
    public static final Deadline DEADLINE_4 = Deadline.builder()
            .id(DEADLINE_ID_4)
            .title("Contestación demanda - Madrid 2040")
            .notificationDate(LocalDate.of(2040, 3, 5))
            .days(5)
            .dayCountType(DayCountType.WORKING)
            .region(MADRID)
            .city(MADRID)
            .status(DeadlineStatus.PENDING)
            .createdAt(LocalDateTime.of(2040, 3, 5, 9, 0))
            .dueDate(LocalDate.of(2040, 3, 12))
            .nonWorkingDays(List.of())
            .userSnapshot(USER_0)
            .build();

    public static final UUID DEADLINE_ID_5 = UUID.fromString(PREFIX + "1005");
    public static final Deadline DEADLINE_5 = Deadline.builder()
            .id(DEADLINE_ID_5)
            .title("Recurso de apelación - Barcelona")
            .courtFileNumber("301/2020")
            .notificationDate(LocalDate.of(2020, 9, 8))
            .days(3)
            .dayCountType(DayCountType.WORKING)
            .region(CATALUNA)
            .city("Barcelona")
            .status(DeadlineStatus.PENDING)
            .createdAt(LocalDateTime.of(2020, 9, 8, 9, 0))
            .dueDate(LocalDate.of(2020, 9, 14))
            .nonWorkingDays(List.of(NON_WORKING_DAY_12))
            .userSnapshot(USER_1)
            .build();

    public static final UUID DEADLINE_ID_6 = UUID.fromString(PREFIX + "1006");
    public static final Deadline DEADLINE_6 = Deadline.builder()
            .id(DEADLINE_ID_6)
            .title("Diligencias urgentes - Barcelona")
            .notificationDate(LocalDate.of(2020, 4, 1))
            .days(7)
            .dayCountType(DayCountType.CALENDAR)
            .region(CATALUNA)
            .city("Barcelona")
            .status(DeadlineStatus.PENDING)
            .createdAt(LocalDateTime.of(2020, 4, 1, 9, 0))
            .dueDate(LocalDate.of(2020, 4, 8))
            .nonWorkingDays(List.of())
            .userSnapshot(USER_1)
            .build();

    public static final UUID DEADLINE_ID_7 = UUID.fromString(PREFIX + "1007");
    public static final Deadline DEADLINE_7 = Deadline.builder()
            .id(DEADLINE_ID_7)
            .title("Contestación demanda - Barcelona 2040")
            .notificationDate(LocalDate.of(2040, 2, 6))
            .days(4)
            .dayCountType(DayCountType.WORKING)
            .region(CATALUNA)
            .city("Barcelona")
            .status(DeadlineStatus.PENDING)
            .createdAt(LocalDateTime.of(2040, 2, 6, 9, 0))
            .dueDate(LocalDate.of(2040, 2, 10))
            .nonWorkingDays(List.of())
            .userSnapshot(USER_1)
            .build();

    public static final UUID DEADLINE_ID_8 = UUID.fromString(PREFIX + "1008");
    public static final Deadline DEADLINE_8 = Deadline.builder()
            .id(DEADLINE_ID_8)
            .title("Proposición de prueba - Alcobendas")
            .courtFileNumber("401/2020")
            .notificationDate(LocalDate.of(2020, 10, 5))
            .days(4)
            .dayCountType(DayCountType.WORKING)
            .region(MADRID)
            .city("Alcobendas")
            .status(DeadlineStatus.PENDING)
            .createdAt(LocalDateTime.of(2020, 10, 5, 9, 0))
            .dueDate(LocalDate.of(2020, 10, 9))
            .nonWorkingDays(List.of())
            .userSnapshot(USER_2)
            .build();

    /**
     * Comparte número de autos con DEADLINE_8: un mismo procedimiento judicial engloba varios plazos.
     */
    public static final UUID DEADLINE_ID_9 = UUID.fromString(PREFIX + "1009");
    public static final Deadline DEADLINE_9 = Deadline.builder()
            .id(DEADLINE_ID_9)
            .title("Conclusiones - Alcobendas")
            .courtFileNumber("401/2020")
            .notificationDate(LocalDate.of(2020, 11, 3))
            .days(2)
            .dayCountType(DayCountType.WORKING)
            .region(MADRID)
            .city("Alcobendas")
            .status(DeadlineStatus.PENDING)
            .createdAt(LocalDateTime.of(2020, 11, 3, 9, 0))
            .dueDate(LocalDate.of(2020, 11, 5))
            .nonWorkingDays(List.of())
            .userSnapshot(USER_2)
            .build();

    private static UserSnapshot user(UUID id, String mobile, String firstName) {
        return UserSnapshot.builder()
                .id(id)
                .mobile(mobile)
                .firstName(firstName)
                .build();
    }

    private final NonWorkingDayRepository nonWorkingDayRepository;
    private final DeadlineRepository deadlineRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load Deadline Calculator -----------");
        this.seedNonWorkingDays();
        this.seedDeadlines();
    }

    private void seedNonWorkingDays() {
        List<NonWorkingDayEntity> nonWorkingDays = List.of(
                        NON_WORKING_DAY_0, NON_WORKING_DAY_1, NON_WORKING_DAY_2, NON_WORKING_DAY_3,
                        NON_WORKING_DAY_4, NON_WORKING_DAY_5, NON_WORKING_DAY_6, NON_WORKING_DAY_7,
                        NON_WORKING_DAY_8, NON_WORKING_DAY_9, NON_WORKING_DAY_10,
                        NON_WORKING_DAY_11, NON_WORKING_DAY_12).stream()
                .filter(nonWorkingDay -> !this.nonWorkingDayRepository.existsById(nonWorkingDay.getId()))
                .map(NonWorkingDayEntity::new)
                .toList();
        this.nonWorkingDayRepository.saveAll(nonWorkingDays);
        log.warn("        ------- non working days: {} added", nonWorkingDays.size());
    }

    private void seedDeadlines() {
        List<DeadlineEntity> deadlines = List.of(
                        DEADLINE_0, DEADLINE_1, DEADLINE_2, DEADLINE_3, DEADLINE_4,
                        DEADLINE_5, DEADLINE_6, DEADLINE_7, DEADLINE_8, DEADLINE_9).stream()
                .filter(deadline -> !this.deadlineRepository.existsById(deadline.getId()))
                .map(this::toEntity)
                .toList();
        this.deadlineRepository.saveAll(deadlines);
        log.warn("        ------- deadlines: {} added", deadlines.size());
    }

    private DeadlineEntity toEntity(Deadline deadline) {
        DeadlineEntity entity = new DeadlineEntity(deadline);
        entity.setNonWorkingDays(deadline.getNonWorkingDays().stream()
                .map(nonWorkingDay -> this.nonWorkingDayRepository.getReferenceById(nonWorkingDay.getId()))
                .collect(Collectors.toCollection(ArrayList::new)));
        return entity;
    }
}
