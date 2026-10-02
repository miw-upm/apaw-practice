package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.courthearing.postgres.CourtEntity;
import es.upm.miw.apaw.adapters.out.courthearing.postgres.CourtHearingEntity;
import es.upm.miw.apaw.adapters.out.courthearing.postgres.CourtHearingRepository;
import es.upm.miw.apaw.adapters.out.courthearing.postgres.CourtRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.courthearing.Court;
import es.upm.miw.apaw.domain.model.courthearing.CourtHearing;
import es.upm.miw.apaw.domain.model.courthearing.CourtHearingStatus;
import es.upm.miw.apaw.domain.model.courthearing.CourtHearingType;
import es.upm.miw.apaw.domain.model.courthearing.CourtType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(2)
@RequiredArgsConstructor
public class CourtHearingSeederForDev implements ApplicationRunner {
    private static final String COURT_PREFIX = "cccccccc-dddd-eeee-ffff-aaaaaaaa";
    public static final UUID COURT_ID_0 = UUID.fromString(COURT_PREFIX + "0000");
    public static final Court COURT_0 = Court.builder()
            .id(COURT_ID_0)
            .name("Tribunal Supremo")
            .address("Plaza de la Villa de París, s/n")
            .city("Madrid")
            .phone("915000000")
            .openingTime(LocalTime.of(9, 0))
            .closingTime(LocalTime.of(17, 0))
            .type(CourtType.SUPREME)
            .build();
    public static final UUID COURT_ID_1 = UUID.fromString(COURT_PREFIX + "0001");
    public static final Court COURT_1 = Court.builder()
            .id(COURT_ID_1)
            .name("Audiencia Provincial de Barcelona")
            .address("Passeig Lluís Companys, 14-16")
            .city("Barcelona")
            .phone("935000001")
            .openingTime(LocalTime.of(8, 30))
            .closingTime(LocalTime.of(15, 0))
            .type(CourtType.APPEAL)
            .build();
    public static final UUID COURT_ID_2 = UUID.fromString(COURT_PREFIX + "0002");
    public static final Court COURT_2 = Court.builder()
            .id(COURT_ID_2)
            .name("Juzgado de Familia nº 3")
            .address("Calle del Pozas, 12")
            .city("Madrid")
            .phone("915000002")
            .openingTime(LocalTime.of(9, 0))
            .closingTime(LocalTime.of(14, 0))
            .type(CourtType.FAMILY)
            .build();
    public static final UUID COURT_ID_3 = UUID.fromString(COURT_PREFIX + "0003");
    public static final Court COURT_3 = Court.builder()
            .id(COURT_ID_3)
            .name("Juzgado de lo Penal nº 1")
            .address("Avenida del Puerto, 25")
            .city("Valencia")
            .phone("965000003")
            .openingTime(LocalTime.of(9, 30))
            .closingTime(LocalTime.of(14, 30))
            .type(CourtType.CRIMINAL)
            .build();
    public static final UUID COURT_ID_4 = UUID.fromString(COURT_PREFIX + "0004");
    public static final Court COURT_4 = Court.builder()
            .id(COURT_ID_4)
            .name("Juzgado Municipal de Getafe")
            .address("Calle Ramón y Cajal, 8")
            .city("Getafe")
            .type(CourtType.MUNICIPAL)
            .build();

    private static final String USER_PREFIX = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff";
    private static final UserSnapshot USER_0 = user("0000", "600000100", "cliente0");
    private static final UserSnapshot USER_1 = user("0001", "600000101", "cliente1");
    private static final UserSnapshot USER_2 = user("0002", "600000102", "cliente2");
    private static final UserSnapshot USER_3 = user("0003", "600000103", "cliente3");
    private static final UserSnapshot USER_4 = user("0004", "600000104", "cliente4");

    private static final String HEARING_PREFIX = "dddddddd-eeee-ffff-aaaa-bbbbbbbb";
    public static final UUID HEARING_ID_0 = UUID.fromString(HEARING_PREFIX + "0000");
    public static final CourtHearing HEARING_0 = CourtHearing.builder()
            .id(HEARING_ID_0)
            .date(LocalDateTime.of(2025, 3, 12, 10, 0))
            .roomNumber("A-101")
            .transcript("Hearing held; parties presented their initial allegations")
            .durationMinutes(90)
            .openToPublic(true)
            .remote(false)
            .type(CourtHearingType.TRIAL)
            .status(CourtHearingStatus.COMPLETED)
            .attendees(List.of(USER_0, USER_1))
            .build();
    public static final UUID HEARING_ID_1 = UUID.fromString(HEARING_PREFIX + "0001");
    public static final CourtHearing HEARING_1 = CourtHearing.builder()
            .id(HEARING_ID_1)
            .date(LocalDateTime.of(2026, 11, 4, 9, 30))
            .roomNumber("A-102")
            .durationMinutes(60)
            .openToPublic(true)
            .remote(false)
            .type(CourtHearingType.APPEAL)
            .status(CourtHearingStatus.SCHEDULED)
            .attendees(List.of(USER_0, USER_2))
            .build();
    public static final UUID HEARING_ID_2 = UUID.fromString(HEARING_PREFIX + "0002");
    public static final CourtHearing HEARING_2 = CourtHearing.builder()
            .id(HEARING_ID_2)
            .date(LocalDateTime.of(2026, 12, 15, 12, 0))
            .roomNumber("B-201")
            .durationMinutes(45)
            .openToPublic(false)
            .remote(true)
            .type(CourtHearingType.MOTION)
            .status(CourtHearingStatus.SCHEDULED)
            .attendees(List.of(USER_1, USER_3, USER_4))
            .build();
    public static final UUID HEARING_ID_3 = UUID.fromString(HEARING_PREFIX + "0003");
    public static final CourtHearing HEARING_3 = CourtHearing.builder()
            .id(HEARING_ID_3)
            .date(LocalDateTime.of(2025, 5, 20, 11, 0))
            .roomNumber("C-005")
            .durationMinutes(120)
            .openToPublic(false)
            .remote(false)
            .type(CourtHearingType.PRETRIAL)
            .status(CourtHearingStatus.CANCELLED)
            .attendees(List.of(USER_2))
            .build();
    public static final UUID HEARING_ID_4 = UUID.fromString(HEARING_PREFIX + "0004");
    public static final CourtHearing HEARING_4 = CourtHearing.builder()
            .id(HEARING_ID_4)
            .date(LocalDateTime.of(2026, 10, 28, 10, 30))
            .roomNumber("C-006")
            .durationMinutes(75)
            .openToPublic(true)
            .remote(false)
            .type(CourtHearingType.STATUS)
            .status(CourtHearingStatus.SCHEDULED)
            .attendees(List.of(USER_3, USER_4))
            .build();
    public static final UUID HEARING_ID_5 = UUID.fromString(HEARING_PREFIX + "0005");
    public static final CourtHearing HEARING_5 = CourtHearing.builder()
            .id(HEARING_ID_5)
            .date(LocalDateTime.of(2025, 9, 9, 9, 0))
            .roomNumber("D-010")
            .transcript("Custody arrangements agreed by both parties")
            .durationMinutes(50)
            .openToPublic(false)
            .remote(true)
            .type(CourtHearingType.STATUS)
            .status(CourtHearingStatus.COMPLETED)
            .attendees(List.of(USER_0, USER_4))
            .build();
    public static final UUID HEARING_ID_6 = UUID.fromString(HEARING_PREFIX + "0006");
    public static final CourtHearing HEARING_6 = CourtHearing.builder()
            .id(HEARING_ID_6)
            .date(LocalDateTime.of(2025, 7, 1, 16, 0))
            .roomNumber("E-003")
            .transcript("Sentence read in open court")
            .durationMinutes(30)
            .openToPublic(true)
            .remote(false)
            .type(CourtHearingType.SENTENCING)
            .status(CourtHearingStatus.COMPLETED)
            .attendees(List.of(USER_1, USER_2, USER_3))
            .build();

    private static final List<SeededHearing> HEARINGS = List.of(
            new SeededHearing(COURT_ID_0, HEARING_0),
            new SeededHearing(COURT_ID_0, HEARING_1),
            new SeededHearing(COURT_ID_0, HEARING_2),
            new SeededHearing(COURT_ID_1, HEARING_3),
            new SeededHearing(COURT_ID_1, HEARING_4),
            new SeededHearing(COURT_ID_2, HEARING_5),
            new SeededHearing(COURT_ID_3, HEARING_6));

    private final CourtRepository courtRepository;
    private final CourtHearingRepository courtHearingRepository;

    private static UserSnapshot user(String idSuffix, String mobile, String firstName) {
        return UserSnapshot.builder()
                .id(UUID.fromString(USER_PREFIX + idSuffix))
                .mobile(mobile)
                .firstName(firstName)
                .build();
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load from JAVA (courts and court hearings) -----------");
        this.seedCourts();
        this.seedCourtHearings();
    }

    private void seedCourts() {
        List<CourtEntity> courts = List.of(COURT_0, COURT_1, COURT_2, COURT_3, COURT_4).stream()
                .filter(court -> !this.courtRepository.existsById(court.getId()))
                .map(CourtEntity::new)
                .toList();
        this.courtRepository.saveAll(courts);
        log.warn("        ------- courts: {} added", courts.size());
    }

    private void seedCourtHearings() {
        List<CourtHearingEntity> courtHearings = HEARINGS.stream()
                .filter(seeded -> !this.courtHearingRepository.existsById(seeded.hearing().getId()))
                .map(this::toEntity)
                .toList();
        this.courtHearingRepository.saveAll(courtHearings);
        log.warn("        ------- court hearings: {} added", courtHearings.size());
    }

    private CourtHearingEntity toEntity(SeededHearing seeded) {
        CourtHearingEntity entity = new CourtHearingEntity(seeded.hearing());
        entity.setCourt(this.courtRepository.getReferenceById(seeded.courtId()));
        return entity;
    }

    private record SeededHearing(UUID courtId, CourtHearing hearing) {
    }
}