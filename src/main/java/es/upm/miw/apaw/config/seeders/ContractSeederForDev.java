
package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.contract.postgres.ClauseEntity;
import es.upm.miw.apaw.adapters.out.contract.postgres.ClauseRepository;
import es.upm.miw.apaw.adapters.out.contract.postgres.ContractEntity;
import es.upm.miw.apaw.adapters.out.contract.postgres.ContractRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.contract.Clause;
import es.upm.miw.apaw.domain.model.contract.ClauseType;
import es.upm.miw.apaw.domain.model.contract.Contract;
import es.upm.miw.apaw.domain.model.contract.ContractType;
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

    public static final UUID ID_7 = UUID.fromString(PREFIX + "0007");
    public static final Clause CLAUSE_7 = Clause.builder()
            .id(ID_7)
            .title("Responsabilidad contractual")
            .type(ClauseType.LIABILITY)
            .content("Las partes responderán de los daños derivados del incumplimiento de sus obligaciones contractuales.")
            .effectiveFrom(LocalDate.of(2026, 6, 1))
            .effectiveUntil(LocalDate.of(2027, 6, 1))
            .notes("Cláusula utilizada para comprobar el recuento de cláusulas activas.")
            .version(1)
            .build();

    public static final UUID ID_8 = UUID.fromString(PREFIX + "0008");
    public static final Clause CLAUSE_8 = Clause.builder()
            .id(ID_8)
            .title("Resolución del contrato")
            .type(ClauseType.TERMINATION)
            .content("El contrato podrá resolverse en los supuestos establecidos por las partes.")
            .effectiveFrom(LocalDate.of(2025, 1, 1))
            .effectiveUntil(LocalDate.of(2026, 9, 30))
            .notes("Cláusula finalizada para comprobar que no se contabiliza como activa.")
            .version(1)
            .build();

    public static final UUID ID_9 = UUID.fromString(PREFIX + "0009");
    public static final Clause CLAUSE_9 = Clause.builder()
            .id(ID_9)
            .title("Condiciones económicas")
            .type(ClauseType.PAYMENT)
            .content("Las condiciones económicas se aplicarán conforme a lo establecido en el contrato.")
            .effectiveFrom(LocalDate.of(2026, 11, 1))
            .effectiveUntil(LocalDate.of(2027, 11, 1))
            .notes("Cláusula futura para comprobar el recuento de cláusulas activas.")
            .version(1)
            .build();

    public static final UUID ID_A = UUID.fromString(PREFIX + "000a");
    public static final Clause CLAUSE_A = Clause.builder()
            .id(ID_A)
            .title("Protección de datos adicional")
            .type(ClauseType.DATA_PROTECTION)
            .content("Los datos personales serán tratados conforme a la normativa aplicable.")
            .effectiveFrom(LocalDate.of(2026, 1, 1))
            .effectiveUntil(LocalDate.of(2028, 1, 1))
            .notes("Cláusula activa durante las pruebas del Report.")
            .version(1)
            .build();

    public static final UUID ID_B = UUID.fromString(PREFIX + "000b");
    public static final Clause CLAUSE_B = Clause.builder()
            .id(ID_B)
            .title("Confidencialidad adicional")
            .type(ClauseType.CONFIDENTIALITY)
            .content("Las partes deberán mantener la confidencialidad de toda la información intercambiada durante la vigencia del contrato.")
            .effectiveFrom(LocalDate.of(2026, 1, 1))
            .notes("Cláusula activa sin fecha de finalización.")
            .version(1)
            .build();

    public static final String CONTRACT_PREFIX = "dddddddd-eeee-ffff-aaaa-00000000";
    private static final String USER_PREFIX = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff";

    public static final UUID CONTRACT_ID_0 = UUID.fromString(CONTRACT_PREFIX + "0000");

    public static final Contract CONTRACT_0 = Contract.builder()
            .id(CONTRACT_ID_0)
            .title("Contrato de servicios de consultoría")
            .type(ContractType.SERVICE)
            .startDate(LocalDate.of(2026, 1, 1))
            .endDate(LocalDate.of(2026, 10, 8))
            .amount(new BigDecimal("1500.00"))
            .automaticRenewal(false)
            .createdAt(LocalDateTime.of(2026, 1, 1, 10, 0))
            .clauses(List.of(CLAUSE_7, CLAUSE_A))
            .userSnapshot(user("0000", "600000100", "cliente0"))
            .build();

    public static final UUID CONTRACT_ID_1 = UUID.fromString(CONTRACT_PREFIX + "0001");

    public static final Contract CONTRACT_1 = Contract.builder()
            .id(CONTRACT_ID_1)
            .title("Contrato comercial de suministro")
            .type(ContractType.COMMERCIAL)
            .startDate(LocalDate.of(2026, 2, 1))
            .endDate(LocalDate.of(2026, 10, 20))
            .amount(new BigDecimal("3200.00"))
            .automaticRenewal(false)
            .createdAt(LocalDateTime.of(2026, 2, 1, 11, 0))
            .clauses(List.of(CLAUSE_7))
            .userSnapshot(user("0000", "600000100", "cliente0"))
            .build();

    public static final UUID CONTRACT_ID_2 = UUID.fromString(CONTRACT_PREFIX + "0002");

    public static final Contract CONTRACT_2 = Contract.builder()
            .id(CONTRACT_ID_2)
            .title("Contrato laboral de mantenimiento")
            .type(ContractType.EMPLOYMENT)
            .startDate(LocalDate.of(2026, 3, 1))
            .endDate(LocalDate.of(2026, 10, 15))
            .amount(new BigDecimal("2100.00"))
            .automaticRenewal(false)
            .createdAt(LocalDateTime.of(2026, 3, 1, 9, 0))
            .clauses(List.of(CLAUSE_8))
            .userSnapshot(user("0001", "600000101", "cliente1"))
            .build();

    public static final UUID CONTRACT_ID_3 = UUID.fromString(CONTRACT_PREFIX + "0003");

    public static final Contract CONTRACT_3 = Contract.builder()
            .id(CONTRACT_ID_3)
            .title("Contrato de alquiler de oficinas")
            .type(ContractType.RENTAL)
            .startDate(LocalDate.of(2026, 1, 15))
            .endDate(LocalDate.of(2026, 11, 2))
            .amount(new BigDecimal("1800.00"))
            .automaticRenewal(false)
            .createdAt(LocalDateTime.of(2026, 1, 15, 10, 0))
            .clauses(List.of(CLAUSE_7, CLAUSE_9))
            .userSnapshot(user("0002", "600000102", "cliente2"))
            .build();

    public static final UUID CONTRACT_ID_4 = UUID.fromString(CONTRACT_PREFIX + "0004");

    public static final Contract CONTRACT_4 = Contract.builder()
            .id(CONTRACT_ID_4)
            .title("Contrato de servicios renovable")
            .type(ContractType.SERVICE)
            .startDate(LocalDate.of(2026, 2, 1))
            .endDate(LocalDate.of(2026, 11, 2))
            .amount(new BigDecimal("2500.00"))
            .automaticRenewal(true)
            .createdAt(LocalDateTime.of(2026, 2, 1, 12, 0))
            .clauses(List.of(CLAUSE_7))
            .userSnapshot(user("0003", "600000103", "cliente3"))
            .build();

    public static final UUID CONTRACT_ID_5 = UUID.fromString(CONTRACT_PREFIX + "0005");

    public static final Contract CONTRACT_5 = Contract.builder()
            .id(CONTRACT_ID_5)
            .title("Contrato comercial de distribución")
            .type(ContractType.COMMERCIAL)
            .startDate(LocalDate.of(2026, 1, 1))
            .endDate(LocalDate.of(2026, 11, 15))
            .amount(new BigDecimal("4500.00"))
            .automaticRenewal(false)
            .createdAt(LocalDateTime.of(2026, 1, 1, 13, 0))
            .clauses(List.of(CLAUSE_A))
            .userSnapshot(user("0004", "600000104", "cliente4"))
            .build();

    public static final UUID CONTRACT_ID_6 = UUID.fromString(CONTRACT_PREFIX + "0006");

    public static final Contract CONTRACT_6 = Contract.builder()
            .id(CONTRACT_ID_6)
            .title("Contrato de prestación de servicios")
            .type(ContractType.SERVICE)
            .startDate(LocalDate.of(2026, 1, 1))
            .endDate(LocalDate.of(2026, 10, 3))
            .amount(new BigDecimal("1200.00"))
            .automaticRenewal(false)
            .createdAt(LocalDateTime.of(2026, 1, 1, 14, 0))
            .clauses(List.of(CLAUSE_7, CLAUSE_8))
            .userSnapshot(user("0005", "600000105", "cliente5"))
            .build();

    public static final UUID CONTRACT_ID_7 = UUID.fromString(CONTRACT_PREFIX + "0007");

    public static final Contract CONTRACT_7 = Contract.builder()
            .id(CONTRACT_ID_7)
            .title("Contrato de servicios generales")
            .type(ContractType.OTHER)
            .startDate(LocalDate.of(2026, 4, 1))
            .endDate(LocalDate.of(2026, 10, 25))
            .amount(new BigDecimal("1750.00"))
            .automaticRenewal(false)
            .createdAt(LocalDateTime.of(2026, 4, 1, 10, 0))
            .clauses(List.of(CLAUSE_7))
            .userSnapshot(user("0006", "600000106", "Cliente6"))
            .build();

    public static final UUID CONTRACT_ID_8 = UUID.fromString(CONTRACT_PREFIX + "0008");

    public static final Contract CONTRACT_8 = Contract.builder()
            .id(CONTRACT_ID_8)
            .title("Contrato laboral indefinido")
            .type(ContractType.EMPLOYMENT)
            .startDate(LocalDate.of(2026, 1, 1))
            .endDate(null)
            .amount(new BigDecimal("2800.00"))
            .automaticRenewal(false)
            .createdAt(LocalDateTime.of(2026, 1, 1, 9, 0))
            .clauses(List.of(CLAUSE_9))
            .userSnapshot(user("0007", "600000107", "cliente7"))
            .build();

    public static final UUID CONTRACT_ID_9 = UUID.fromString(CONTRACT_PREFIX + "0009");

    public static final Contract CONTRACT_9 = Contract.builder()
            .id(CONTRACT_ID_9)
            .title("Contrato de alquiler finalizado")
            .type(ContractType.RENTAL)
            .startDate(LocalDate.of(2025, 1, 1))
            .endDate(LocalDate.of(2026, 9, 30))
            .amount(new BigDecimal("1600.00"))
            .automaticRenewal(false)
            .createdAt(LocalDateTime.of(2025, 1, 1, 10, 0))
            .clauses(List.of(CLAUSE_8))
            .userSnapshot(user("0008", "600000108", "cliente8"))
            .build();

    public static final UUID CONTRACT_ID_10 =
            UUID.fromString(CONTRACT_PREFIX + "0010");

    public static final Contract CONTRACT_10 = Contract.builder()
            .id(CONTRACT_ID_10)
            .title("Contrato de servicios de consultoría")
            .type(ContractType.SERVICE)
            .startDate(LocalDate.of(2025, 1, 1))
            .endDate(LocalDate.of(2026, 8, 20))
            .amount(new BigDecimal("1900.00"))
            .automaticRenewal(false)
            .createdAt(LocalDateTime.of(2025, 1, 1, 10, 0))
            .clauses(List.of(CLAUSE_3))
            .userSnapshot(user("0000", "600000100", "cliente0"))
            .build();

    public static final UUID CONTRACT_ID_11 =
            UUID.fromString(CONTRACT_PREFIX + "0011");

    public static final Contract CONTRACT_11 = Contract.builder()
            .id(CONTRACT_ID_11)
            .title("Contrato de suministro energético")
            .type(ContractType.COMMERCIAL)
            .startDate(LocalDate.of(2025, 2, 1))
            .endDate(LocalDate.of(2026, 9, 15))
            .amount(new BigDecimal("2700.00"))
            .automaticRenewal(false)
            .createdAt(LocalDateTime.of(2025, 2, 1, 11, 0))
            .clauses(List.of(CLAUSE_0))
            .userSnapshot(user("0001", "600000101", "cliente1"))
            .build();

    public static final UUID CONTRACT_ID_12 =
            UUID.fromString(CONTRACT_PREFIX + "0012");

    public static final Contract CONTRACT_12 = Contract.builder()
            .id(CONTRACT_ID_12)
            .title("Contrato de mantenimiento de instalaciones")
            .type(ContractType.SERVICE)
            .startDate(LocalDate.of(2025, 3, 1))
            .endDate(LocalDate.of(2026, 7, 31))
            .amount(new BigDecimal("2100.00"))
            .automaticRenewal(false)
            .createdAt(LocalDateTime.of(2025, 3, 1, 9, 0))
            .clauses(List.of(CLAUSE_7))
            .userSnapshot(user("0002", "600000102", "cliente2"))
            .build();

    public static final UUID CONTRACT_ID_13 =
            UUID.fromString(CONTRACT_PREFIX + "0013");

    public static final Contract CONTRACT_13 = Contract.builder()
            .id(CONTRACT_ID_13)
            .title("Contrato de asesoramiento fiscal")
            .type(ContractType.SERVICE)
            .startDate(LocalDate.of(2026, 1, 1))
            .endDate(LocalDate.of(2026, 12, 20))
            .amount(new BigDecimal("2300.00"))
            .automaticRenewal(false)
            .createdAt(LocalDateTime.of(2026, 1, 1, 10, 0))
            .clauses(List.of(CLAUSE_3))
            .userSnapshot(user("0003", "600000103", "cliente3"))
            .build();

    public static final UUID CONTRACT_ID_14 =
            UUID.fromString(CONTRACT_PREFIX + "0014");

    public static final Contract CONTRACT_14 = Contract.builder()
            .id(CONTRACT_ID_14)
            .title("Contrato de gestión administrativa")
            .type(ContractType.OTHER)
            .startDate(LocalDate.of(2025, 4, 1))
            .endDate(LocalDate.of(2026, 5, 30))
            .amount(new BigDecimal("1400.00"))
            .automaticRenewal(false)
            .createdAt(LocalDateTime.of(2025, 4, 1, 10, 0))
            .clauses(List.of(CLAUSE_A))
            .userSnapshot(user("0004", "600000104", "cliente4"))
            .build();

    public static final UUID CONTRACT_ID_15 =
            UUID.fromString(CONTRACT_PREFIX + "0015");

    public static final Contract CONTRACT_15 = Contract.builder()
            .id(CONTRACT_ID_15)
            .title("Contrato de soporte técnico")
            .type(ContractType.SERVICE)
            .startDate(LocalDate.of(2026, 2, 1))
            .endDate(LocalDate.of(2027, 2, 28))
            .amount(new BigDecimal("2600.00"))
            .automaticRenewal(true)
            .createdAt(LocalDateTime.of(2026, 2, 1, 11, 0))
            .clauses(List.of(CLAUSE_2))
            .userSnapshot(user("0005", "600000105", "cliente5"))
            .build();

    public static final UUID CONTRACT_ID_16 =
            UUID.fromString(CONTRACT_PREFIX + "0016");

    public static final Contract CONTRACT_16 = Contract.builder()
            .id(CONTRACT_ID_16)
            .title("Contrato de colaboración empresarial")
            .type(ContractType.COMMERCIAL)
            .startDate(LocalDate.of(2025, 6, 1))
            .endDate(null)
            .amount(new BigDecimal("3500.00"))
            .automaticRenewal(false)
            .createdAt(LocalDateTime.of(2025, 6, 1, 12, 0))
            .clauses(List.of(CLAUSE_0))
            .userSnapshot(user("0000", "600000100", "cliente0"))
            .build();

    public static final UUID CONTRACT_ID_17 =
            UUID.fromString(CONTRACT_PREFIX + "0017");

    public static final Contract CONTRACT_17 = Contract.builder()
            .id(CONTRACT_ID_17)
            .title("Contrato de mantenimiento informático")
            .type(ContractType.SERVICE)
            .startDate(LocalDate.of(2026, 1, 15))
            .endDate(LocalDate.of(2026, 10, 2))
            .amount(new BigDecimal("1800.00"))
            .automaticRenewal(false)
            .createdAt(LocalDateTime.of(2026, 1, 15, 9, 0))
            .clauses(List.of(CLAUSE_3))
            .userSnapshot(user("0001", "600000101", "cliente1"))
            .build();

    public static final UUID CONTRACT_ID_18 =
            UUID.fromString(CONTRACT_PREFIX + "0018");

    public static final Contract CONTRACT_18 = Contract.builder()
            .id(CONTRACT_ID_18)
            .title("Contrato de asesoramiento empresarial")
            .type(ContractType.SERVICE)
            .startDate(LocalDate.of(2026, 3, 1))
            .endDate(LocalDate.of(2026, 11, 10))
            .amount(new BigDecimal("2900.00"))
            .automaticRenewal(false)
            .createdAt(LocalDateTime.of(2026, 3, 1, 10, 0))
            .clauses(List.of(CLAUSE_7))
            .userSnapshot(user("0003", "600000103", "cliente3"))
            .build();

    public static final UUID CONTRACT_ID_19 =
            UUID.fromString(CONTRACT_PREFIX + "0019");

    public static final Contract CONTRACT_19 = Contract.builder()
            .id(CONTRACT_ID_19)
            .title("Contrato de servicios profesionales")
            .type(ContractType.SERVICE)
            .startDate(LocalDate.of(2025, 5, 1))
            .endDate(LocalDate.of(2026, 6, 15))
            .amount(new BigDecimal("2200.00"))
            .automaticRenewal(false)
            .createdAt(LocalDateTime.of(2025, 5, 1, 13, 0))
            .clauses(List.of(CLAUSE_3))
            .userSnapshot(user("0005", "600000105", "cliente5"))
            .build();

    private final ClauseRepository clauseRepository;
    private final ContractRepository contractRepository;

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
        log.warn("------- Initial Load from JAVA -----------");
        this.seedClauses();
        this.seedContracts();
    }

    private void seedClauses() {
        List<ClauseEntity> clauses = List.of(
                        CLAUSE_0, CLAUSE_1, CLAUSE_2,
                        CLAUSE_3, CLAUSE_4, CLAUSE_5,
                        CLAUSE_6, CLAUSE_7, CLAUSE_8,
                        CLAUSE_9, CLAUSE_A, CLAUSE_B
                ).stream()
                .filter(clause -> !this.clauseRepository.existsById(clause.getId()))
                .map(ClauseEntity::new)
                .toList();

        this.clauseRepository.saveAll(clauses);

        log.warn("        ------- clauses: {} added", clauses.size());
    }

    private void seedContracts() {
        List<ContractEntity> contracts = List.of(
                        CONTRACT_0, CONTRACT_1, CONTRACT_2,
                        CONTRACT_3, CONTRACT_4, CONTRACT_5,
                        CONTRACT_6, CONTRACT_7, CONTRACT_8,
                        CONTRACT_9, CONTRACT_10, CONTRACT_11,
                        CONTRACT_12, CONTRACT_13, CONTRACT_14,
                        CONTRACT_15, CONTRACT_16, CONTRACT_17,
                        CONTRACT_18, CONTRACT_19
                ).stream()
                .filter(contract -> !this.contractRepository.existsById(contract.getId()))
                .map(this::toEntity)
                .toList();

        this.contractRepository.saveAll(contracts);

        log.warn("        ------- contracts: {} added", contracts.size());
    }

    private ContractEntity toEntity(Contract contract) {
        ContractEntity entity = new ContractEntity(contract);
        entity.setClauses(contract.getClauses().stream()
                .map(clause -> this.clauseRepository.getReferenceById(clause.getId()))
                .collect(Collectors.toCollection(ArrayList::new)));
        return entity;
    }
}