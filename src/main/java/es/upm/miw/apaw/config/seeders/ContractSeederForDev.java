
package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.contract.postgres.ClauseEntity;
import es.upm.miw.apaw.adapters.out.contract.postgres.ClauseRepository;
import es.upm.miw.apaw.domain.model.contract.Clause;
import es.upm.miw.apaw.domain.model.contract.ClauseType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(1)
@RequiredArgsConstructor
public class ContractSeederForDev implements ApplicationRunner {

    public static final String PREFIX = "cccccccc-dddd-eeee-ffff-00000000";

    public static final UUID ID_0 = UUID.fromString(PREFIX + "0000");
    public static final Clause CLAUSE_0 = Clause.builder()
            .id(ID_0)
            .title("Confidencialidad")
            .type(ClauseType.CONFIDENTIALITY)
            .content("Las partes se comprometen a mantener la confidencialidad de la información compartida.")
            .effectiveFrom(LocalDate.of(2025, 1, 1))
            .effectiveUntil(LocalDate.of(2027, 12, 31))
            .notes("Protección de información sensible.")
            .version(1)
            .build();

    public static final UUID ID_1 = UUID.fromString(PREFIX + "0001");
    public static final Clause CLAUSE_1 = Clause.builder()
            .id(ID_1)
            .title("Penalización por incumplimiento")
            .type(ClauseType.PENALTY)
            .content("El incumplimiento de las obligaciones contractuales podrá dar lugar a una penalización.")
            .effectiveFrom(LocalDate.of(2025, 2, 1))
            .notes("La penalización deberá ajustarse a lo establecido en el contrato.")
            .version(1)
            .build();

    public static final UUID ID_2 = UUID.fromString(PREFIX + "0002");
    public static final Clause CLAUSE_2 = Clause.builder()
            .id(ID_2)
            .title("Resolución anticipada")
            .type(ClauseType.TERMINATION)
            .content("Cualquiera de las partes podrá solicitar la resolución anticipada en los supuestos previstos.")
            .effectiveFrom(LocalDate.of(2025, 3, 1))
            .effectiveUntil(LocalDate.of(2028, 3, 1))
            .notes("Requiere comunicación previa por escrito.")
            .version(1)
            .build();

    public static final UUID ID_3 = UUID.fromString(PREFIX + "0003");
    public static final Clause CLAUSE_3 = Clause.builder()
            .id(ID_3)
            .title("Condiciones de pago")
            .type(ClauseType.PAYMENT)
            .content("Los pagos se efectuarán conforme a los plazos y condiciones acordados por las partes.")
            .effectiveFrom(LocalDate.of(2025, 4, 1))
            .notes("Se aplicarán las condiciones económicas recogidas en el contrato.")
            .version(1)
            .build();

    public static final UUID ID_4 = UUID.fromString(PREFIX + "0004");
    public static final Clause CLAUSE_4 = Clause.builder()
            .id(ID_4)
            .title("Protección de datos")
            .type(ClauseType.DATA_PROTECTION)
            .content("Las partes tratarán los datos personales de acuerdo con la normativa vigente.")
            .effectiveFrom(LocalDate.of(2025, 5, 1))
            .effectiveUntil(LocalDate.of(2028, 5, 1))
            .notes("Aplicable al tratamiento de datos personales.")
            .version(1)
            .build();

    public static final UUID ID_5 = UUID.fromString(PREFIX + "0005");
    public static final Clause CLAUSE_5 = Clause.builder()
            .id(ID_5)
            .title("Jurisdicción")
            .type(ClauseType.JURISDICTION)
            .content("Las partes se someten a los juzgados y tribunales competentes establecidos en el contrato.")
            .effectiveFrom(LocalDate.of(2025, 6, 1))
            .notes("Determina el fuero aplicable en caso de controversia.")
            .version(1)
            .build();

    public static final UUID ID_6 = UUID.fromString(PREFIX + "0006");
    public static final Clause CLAUSE_6 = Clause.builder()
            .id(ID_6)
            .title("Confidencialidad")
            .type(ClauseType.CONFIDENTIALITY)
            .content("Las partes deberán proteger y no divulgar la información confidencial obtenida durante la ejecución del contrato.")
            .effectiveFrom(LocalDate.of(2025, 7, 1))
            .notes("Cláusula adicional de confidencialidad.")
            .version(1)
            .build();

    private final ClauseRepository clauseRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        this.seed();
    }

    private void seed() {
        log.warn("------- Initial Load from JAVA -----------");

        List<ClauseEntity> clauses = List.of(
                        CLAUSE_0, CLAUSE_1, CLAUSE_2,
                        CLAUSE_3, CLAUSE_4, CLAUSE_5,
                        CLAUSE_6
                ).stream()
                .filter(clause -> !this.clauseRepository.existsById(clause.getId()))
                .map(ClauseEntity::new)
                .toList();

        this.clauseRepository.saveAll(clauses);

        log.warn("        ------- clauses: {} added", clauses.size());
    }
}