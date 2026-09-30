package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.courthearing.postgres.CourtEntity;
import es.upm.miw.apaw.adapters.out.courthearing.postgres.CourtRepository;
import es.upm.miw.apaw.domain.model.courthearing.Court;
import es.upm.miw.apaw.domain.model.courthearing.CourtType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(2)
@RequiredArgsConstructor
public class CourtSeederForDev implements ApplicationRunner {
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

    private final CourtRepository courtRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load from JAVA (courts) -----------");
        this.seedCourts();
    }

    private void seedCourts() {
        List<CourtEntity> courts = List.of(COURT_0, COURT_1, COURT_2, COURT_3, COURT_4).stream()
                .filter(court -> !this.courtRepository.existsById(court.getId()))
                .map(CourtEntity::new)
                .toList();
        this.courtRepository.saveAll(courts);
        log.warn("        ------- courts: {} added", courts.size());
    }
}