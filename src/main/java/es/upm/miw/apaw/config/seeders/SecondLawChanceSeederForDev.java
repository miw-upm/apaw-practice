package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.secondlawchance.postgres.DebtEntity;
import es.upm.miw.apaw.adapters.out.secondlawchance.postgres.DebtRepository;
import es.upm.miw.apaw.adapters.out.secondlawchance.postgres.ExonerationCaseEntity;
import es.upm.miw.apaw.adapters.out.secondlawchance.postgres.ExonerationCaseRepository;
import es.upm.miw.apaw.domain.model.secondlawchance.CreditorType;
import es.upm.miw.apaw.domain.model.secondlawchance.Debt;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(6)
@RequiredArgsConstructor
public class SecondLawChanceSeederForDev implements ApplicationRunner {
    public static final String DEBT_PREFIX = "99999999-aaaa-bbbb-cccc-ddddeeee";
    public static final UUID DEBT_ID_0 = UUID.fromString(DEBT_PREFIX + "0000");
    public static final Debt DEBT_0 = Debt.builder()
            .id(DEBT_ID_0)
            .contractNumber("AEAT-2023-0001")
            .issueDate(LocalDate.of(2023, 3, 15))
            .creditorName("Agencia Tributaria")
            .amount(new BigDecimal("4850.20"))
            .type(CreditorType.PUBLIC)
            .guarantee(false)
            .build();
    public static final UUID DEBT_ID_1 = UUID.fromString(DEBT_PREFIX + "0001");
    public static final Debt DEBT_1 = Debt.builder()
            .id(DEBT_ID_1)
            .contractNumber("TGSS-2023-0002")
            .issueDate(LocalDate.of(2023, 6, 30))
            .creditorName("Tesorería General de la Seguridad Social")
            .amount(new BigDecimal("3120.00"))
            .type(CreditorType.PUBLIC)
            .guarantee(false)
            .build();
    public static final UUID DEBT_ID_2 = UUID.fromString(DEBT_PREFIX + "0002");
    public static final Debt DEBT_2 = Debt.builder()
            .id(DEBT_ID_2)
            .contractNumber("MORT-2019-0003")
            .issueDate(LocalDate.of(2019, 9, 10))
            .creditorName("Banco Santander")
            .amount(new BigDecimal("98500.00"))
            .type(CreditorType.PRIVATE)
            .guarantee(true)
            .build();
    public static final UUID DEBT_ID_3 = UUID.fromString(DEBT_PREFIX + "0003");
    public static final Debt DEBT_3 = Debt.builder()
            .id(DEBT_ID_3)
            .contractNumber("LOAN-2022-0004")
            .issueDate(LocalDate.of(2022, 2, 1))
            .creditorName("CaixaBank")
            .amount(new BigDecimal("15750.50"))
            .type(CreditorType.PRIVATE)
            .guarantee(false)
            .build();
    public static final UUID DEBT_ID_4 = UUID.fromString(DEBT_PREFIX + "0004");
    public static final Debt DEBT_4 = Debt.builder()
            .id(DEBT_ID_4)
            .contractNumber("CARD-2024-0005")
            .issueDate(LocalDate.of(2024, 1, 20))
            .creditorName("Cetelem")
            .amount(new BigDecimal("2890.75"))
            .type(CreditorType.PRIVATE)
            .guarantee(false)
            .build();
    public static final UUID DEBT_ID_5 = UUID.fromString(DEBT_PREFIX + "0005");
    public static final Debt DEBT_5 = Debt.builder()
            .id(DEBT_ID_5)
            .contractNumber("UTIL-2024-0006")
            .issueDate(LocalDate.of(2024, 5, 5))
            .creditorName("Endesa Energía")
            .amount(new BigDecimal("640.30"))
            .type(CreditorType.PRIVATE)
            .guarantee(false)
            .build();

    private static final String CASE_PREFIX = "99999999-aaaa-bbbb-cccc-ffff0000";
    public static final UUID CASE_ID_0 = UUID.fromString(CASE_PREFIX + "0000");
    public static final String CASE_NUMBER_0 = "SLC-2025-0001";
    public static final UUID CASE_ID_1 = UUID.fromString(CASE_PREFIX + "0001");
    public static final String CASE_NUMBER_1 = "SLC-2025-0002";

    private static final String USER_PREFIX = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff";
    public static final UUID USER_ID_0 = UUID.fromString(USER_PREFIX + "0000");
    public static final UUID USER_ID_1 = UUID.fromString(USER_PREFIX + "0001");

    private final DebtRepository debtRepository;
    private final ExonerationCaseRepository exonerationCaseRepository;

    @Override
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load from JAVA -----------");
        this.seedDebts();
        this.seedExonerationCases();
    }

    private void seedDebts() {
        List<DebtEntity> debts = List.of(DEBT_0, DEBT_1, DEBT_2, DEBT_3, DEBT_4, DEBT_5).stream()
                .filter(debt -> !this.debtRepository.existsById(debt.getId()))
                .map(DebtEntity::new)
                .toList();
        this.debtRepository.saveAll(debts);
        log.warn("        ------- debts: {} added", debts.size());
    }

    private void seedExonerationCases() {
        List<ExonerationCaseEntity> exonerationCases = List.of(this.exonerationCase0(), this.exonerationCase1())
                .stream()
                .filter(exonerationCase -> !this.exonerationCaseRepository.existsById(exonerationCase.getId()))
                .toList();
        this.exonerationCaseRepository.saveAll(exonerationCases);
        log.warn("        ------- exoneration cases: {} added", exonerationCases.size());
    }

    private ExonerationCaseEntity exonerationCase0() {
        return ExonerationCaseEntity.builder()
                .id(CASE_ID_0)
                .caseNumber(CASE_NUMBER_0)
                .filingDate(LocalDate.of(2025, 2, 10))
                .lawyer("Elena Martínez Ruiz")
                .debts(this.debtReferences(DEBT_ID_0, DEBT_ID_2))
                .userId(USER_ID_0)
                .build();
    }

    private ExonerationCaseEntity exonerationCase1() {
        return ExonerationCaseEntity.builder()
                .id(CASE_ID_1)
                .caseNumber(CASE_NUMBER_1)
                .filingDate(LocalDate.of(2025, 4, 22))
                .resolutionDate(LocalDate.of(2025, 9, 30))
                .debts(this.debtReferences(DEBT_ID_0, DEBT_ID_3))
                .userId(USER_ID_1)
                .build();
    }

    private List<DebtEntity> debtReferences(UUID... ids) {
        return List.of(ids).stream()
                .map(this.debtRepository::getReferenceById)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
