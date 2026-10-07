package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.judicialcourt.postgres.JudicialCourtEntity;
import es.upm.miw.apaw.adapters.out.judicialcourt.postgres.JudicialCourtRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourt;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.JudicialCourtTypeSeederForDev.TYPE_0;
import static es.upm.miw.apaw.config.seeders.JudicialCourtTypeSeederForDev.TYPE_1;
import static es.upm.miw.apaw.config.seeders.JudicialCourtTypeSeederForDev.TYPE_2;
import static es.upm.miw.apaw.config.seeders.JudicialCourtTypeSeederForDev.TYPE_3;
import static es.upm.miw.apaw.config.seeders.JudicialCourtTypeSeederForDev.TYPE_4;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(3)
@RequiredArgsConstructor
public class JudicialCourtSeederForDev implements ApplicationRunner {
    private static final String PREFIX = "ffffffff-eeee-dddd-cccc-ffffeeee";

    public static final UUID ID_0 = UUID.fromString(PREFIX + "0000");
    public static final JudicialCourt COURT_0 = JudicialCourt.builder()
            .id(ID_0)
            .name("Juzgado de Paz nº 1 de Madrid")
            .number(1)
            .address("Plaza Mayor, 1")
            .city("Madrid")
            .postalCode("28001")
            .phone("912345678")
            .email("juzgado-paz-madrid@justicia.es")
            .createdAt(LocalDateTime.of(2025, 1, 10, 9, 0))
            .updatedAt(LocalDateTime.of(2025, 1, 10, 9, 0))
            .type(TYPE_0)
            .status(JudicialCourtStatus.ACTIVE)
            .lawyers(List.of(
                    user("0000", "600000100", "cliente0"),
                    user("0001", "600000101", "cliente1")))
            .build();

    public static final UUID ID_1 = UUID.fromString(PREFIX + "0001");
    public static final JudicialCourt COURT_1 = JudicialCourt.builder()
            .id(ID_1)
            .name("Juzgado de Primera Instancia e Instrucción nº 2 de Madrid")
            .number(2)
            .address("Calle de la Audiencia, 12")
            .city("Madrid")
            .postalCode("28013")
            .phone("913456789")
            .email("jpi2-madrid@justicia.es")
            .createdAt(LocalDateTime.of(2025, 1, 12, 10, 30))
            .updatedAt(LocalDateTime.of(2025, 1, 12, 10, 30))
            .type(TYPE_1)
            .status(JudicialCourtStatus.ACTIVE)
            .lawyers(List.of(
                    user("0002", "600000102", "cliente2"),
                    user("0003", "600000103", "cliente3")))
            .build();

    public static final UUID ID_2 = UUID.fromString(PREFIX + "0002");
    public static final JudicialCourt COURT_2 = JudicialCourt.builder()
            .id(ID_2)
            .name("Juzgado de lo Mercantil nº 3 de Madrid")
            .number(3)
            .address("Calle de la Bolsa, 5")
            .city("Madrid")
            .postalCode("28014")
            .phone("914567890")
            .email("mercantil3-madrid@justicia.es")
            .createdAt(LocalDateTime.of(2025, 2, 5, 11, 0))
            .updatedAt(LocalDateTime.of(2025, 2, 5, 11, 0))
            .type(TYPE_2)
            .status(JudicialCourtStatus.ACTIVE)
            .lawyers(List.of(
                    user("0003", "600000103", "cliente3"),
                    user("0004", "600000104", "cliente4")))
            .build();

    public static final UUID ID_3 = UUID.fromString(PREFIX + "0003");
    public static final JudicialCourt COURT_3 = JudicialCourt.builder()
            .id(ID_3)
            .name("Juzgado de lo Penal nº 4 de Barcelona")
            .number(4)
            .address("Avenida de la Catedral, 22")
            .city("Barcelona")
            .postalCode("08002")
            .phone("932345678")
            .email("penal4-barcelona@justicia.es")
            .createdAt(LocalDateTime.of(2025, 2, 18, 12, 15))
            .updatedAt(LocalDateTime.of(2025, 2, 18, 12, 15))
            .type(TYPE_3)
            .status(JudicialCourtStatus.ACTIVE)
            .lawyers(List.of(
                    user("0001", "600000101", "cliente1"),
                    user("0003", "600000103", "cliente3"),
                    user("0004", "600000104", "cliente4")))
            .build();

    public static final UUID ID_4 = UUID.fromString(PREFIX + "0004");
    public static final JudicialCourt COURT_4 = JudicialCourt.builder()
            .id(ID_4)
            .name("Juzgado de lo Social nº 6 de Valencia")
            .number(6)
            .address("Calle de la Justicia, 18")
            .city("Valencia")
            .postalCode("46002")
            .phone("963456789")
            .email("social6-valencia@justicia.es")
            .createdAt(LocalDateTime.of(2025, 3, 3, 14, 0))
            .updatedAt(LocalDateTime.of(2025, 3, 3, 14, 0))
            .type(TYPE_4)
            .status(JudicialCourtStatus.ACTIVE)
            .lawyers(List.of(
                    user("0003", "600000103", "cliente3"),
                    user("0004", "600000104", "cliente4")))
            .build();

    private final JudicialCourtRepository judicialCourtRepository;

    @Override
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load JudicialCourts -----------");
        this.seedJudicialCourts();
    }

    private void seedJudicialCourts() {
        List<JudicialCourtEntity> judicialCourts = List.of(COURT_0, COURT_1, COURT_2, COURT_3, COURT_4).stream()
                .filter(court -> !this.judicialCourtRepository.existsById(court.getId()))
                .map(JudicialCourtEntity::new)
                .toList();
        this.judicialCourtRepository.saveAll(judicialCourts);
        log.warn("        ------- judicial courts: {} added", judicialCourts.size());
    }

    private static UserSnapshot user(String idSuffix, String mobile, String firstName) {
        return UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff" + idSuffix))
                .mobile(mobile)
                .firstName(firstName)
                .build();
    }
}
