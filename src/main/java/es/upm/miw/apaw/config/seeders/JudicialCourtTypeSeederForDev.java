package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.judicialcourt.postgres.JudicialCourtTypeEntity;
import es.upm.miw.apaw.adapters.out.judicialcourt.postgres.JudicialCourtTypeRepository;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(2)
@RequiredArgsConstructor
public class JudicialCourtTypeSeederForDev implements ApplicationRunner {
    public static final String PREFIX = "eeeeeeee-ffff-aaaa-bbbb-ccccdddd";

    public static final UUID ID_0 = UUID.fromString(PREFIX + "0000");
    public static final JudicialCourtType TYPE_0 = JudicialCourtType.builder()
            .id(ID_0)
            .name("Juzgado de Paz")
            .description("Órgano judicial de competencia territorial para asuntos menores y conciliación")
            .code("JPZ")
            .jurisdiction("Madrid")
            .active(true)
            .build();

    public static final UUID ID_1 = UUID.fromString(PREFIX + "0001");
    public static final JudicialCourtType TYPE_1 = JudicialCourtType.builder()
            .id(ID_1)
            .name("Juzgado de Primera Instancia e Instrucción")
            .description("Juzgado con competencia civil y penal de primera instancia e instrucción")
            .code("JPII")
            .jurisdiction("Madrid")
            .active(true)
            .build();

    public static final UUID ID_2 = UUID.fromString(PREFIX + "0002");
    public static final JudicialCourtType TYPE_2 = JudicialCourtType.builder()
            .id(ID_2)
            .name("Juzgado de lo Mercantil")
            .description("Tribunal especializado en asuntos mercantiles y societarios")
            .code("JLM")
            .jurisdiction("Madrid")
            .active(true)
            .build();

    public static final UUID ID_3 = UUID.fromString(PREFIX + "0003");
    public static final JudicialCourtType TYPE_3 = JudicialCourtType.builder()
            .id(ID_3)
            .name("Juzgado de lo Penal")
            .description("Juzgado especializado en delitos y procedimientos penales")
            .code("JPN")
            .jurisdiction("Barcelona")
            .active(true)
            .build();

    public static final UUID ID_4 = UUID.fromString(PREFIX + "0004");
    public static final JudicialCourtType TYPE_4 = JudicialCourtType.builder()
            .id(ID_4)
            .name("Juzgado de lo Social")
            .description("Juzgado especializado en relaciones laborales y seguridad social")
            .code("JS")
            .jurisdiction("Valencia")
            .active(true)
            .build();

    private final JudicialCourtTypeRepository judicialCourtTypeRepository;

    @Override
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load JudicialCourtTypes -----------");
        this.seedJudicialCourtTypes();
    }

    private void seedJudicialCourtTypes() {
        List<JudicialCourtTypeEntity> judicialCourtTypes = List.of(TYPE_0, TYPE_1, TYPE_2, TYPE_3, TYPE_4).stream()
                .filter(type -> !this.judicialCourtTypeRepository.existsById(type.getId()))
                .map(JudicialCourtTypeEntity::new)
                .toList();
        this.judicialCourtTypeRepository.saveAll(judicialCourtTypes);
        log.warn("        ------- judicial court types: {} added", judicialCourtTypes.size());
    }
}
