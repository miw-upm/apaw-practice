package es.upm.miw.apaw.adapters.out.secondlawchance.postgres;

import es.upm.miw.apaw.domain.model.secondlawchance.CreditorType;
import es.upm.miw.apaw.domain.model.secondlawchance.SharedDebtReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.SecondLawChanceSeederForDev.DEBT_0;
import static es.upm.miw.apaw.config.seeders.SecondLawChanceSeederForDev.DEBT_ID_0;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ExonerationCaseRepositoryIT {
    @Autowired
    private ExonerationCaseRepository exonerationCaseRepository;
    @Autowired
    private DebtRepository debtRepository;

    @Test
    void testFindSharedDebtReportSeeder() {
        List<SharedDebtReport> report = this.exonerationCaseRepository.findSharedDebtReport();

        assertThat(report).extracting(SharedDebtReport::getDebtorCount)
                .isSortedAccordingTo(Comparator.reverseOrder());
        assertThat(report).filteredOn(item -> item.getDebtId().equals(DEBT_ID_0))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getContractNumber()).isEqualTo(DEBT_0.getContractNumber());
                    assertThat(item.getCreditorName()).isEqualTo(DEBT_0.getCreditorName());
                    assertThat(item.getAmount()).isEqualByComparingTo(DEBT_0.getAmount());
                    assertThat(item.getType()).isEqualTo(DEBT_0.getType());
                    assertThat(item.getCaseCount()).isGreaterThanOrEqualTo(2);
                    assertThat(item.getDebtorCount()).isGreaterThanOrEqualTo(2);
                });
    }

    @Test
    void testFindSharedDebtReportCountsDifferentDebtors() {
        DebtEntity debt = this.saveDebt("500.00");
        this.saveCase(UUID.randomUUID(), debt);
        this.saveCase(UUID.randomUUID(), debt);

        List<SharedDebtReport> report = this.exonerationCaseRepository.findSharedDebtReport();

        assertThat(report).filteredOn(item -> item.getDebtId().equals(debt.getId()))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getContractNumber()).isEqualTo(debt.getContractNumber());
                    assertThat(item.getCaseCount()).isEqualTo(2);
                    assertThat(item.getDebtorCount()).isEqualTo(2);
                });
    }

    @Test
    void testFindSharedDebtReportExcludesSameDebtorAndSingleCase() {
        DebtEntity sameDebtor = this.saveDebt("700.00");
        UUID debtorId = UUID.randomUUID();
        this.saveCase(debtorId, sameDebtor);
        this.saveCase(debtorId, sameDebtor);
        DebtEntity singleCase = this.saveDebt("300.00");
        this.saveCase(UUID.randomUUID(), singleCase);

        List<SharedDebtReport> report = this.exonerationCaseRepository.findSharedDebtReport();

        assertThat(report).extracting(SharedDebtReport::getDebtId)
                .doesNotContain(sameDebtor.getId(), singleCase.getId());
    }

    @Test
    void testFindSharedDebtReportOrdersByDebtorCount() {
        DebtEntity twoDebtors = this.saveDebt("900.00");
        this.saveCase(UUID.randomUUID(), twoDebtors);
        this.saveCase(UUID.randomUUID(), twoDebtors);
        DebtEntity threeDebtors = this.saveDebt("100.00");
        this.saveCase(UUID.randomUUID(), threeDebtors);
        this.saveCase(UUID.randomUUID(), threeDebtors);
        this.saveCase(UUID.randomUUID(), threeDebtors);

        List<UUID> ids = this.exonerationCaseRepository.findSharedDebtReport().stream()
                .map(SharedDebtReport::getDebtId)
                .toList();

        assertThat(ids).containsSubsequence(threeDebtors.getId(), twoDebtors.getId());
    }

    @Test
    void testFindSharedDebtReportOrdersByAmountOnTie() {
        UUID firstDebtor = UUID.randomUUID();
        UUID secondDebtor = UUID.randomUUID();
        DebtEntity lowerAmount = this.saveDebt("800.00");
        DebtEntity higherAmount = this.saveDebt("950.00");
        for (DebtEntity debt : List.of(lowerAmount, higherAmount)) {
            this.saveCase(firstDebtor, debt);
            this.saveCase(secondDebtor, debt);
        }

        List<UUID> ids = this.exonerationCaseRepository.findSharedDebtReport().stream()
                .map(SharedDebtReport::getDebtId)
                .toList();

        assertThat(ids).containsSubsequence(higherAmount.getId(), lowerAmount.getId());
    }

    @Test
    void testFindDebtorRowsByDebtIds() {
        UUID firstDebtor = UUID.randomUUID();
        UUID secondDebtor = UUID.randomUUID();
        DebtEntity shared = this.saveDebt("400.00");
        this.saveCase(firstDebtor, shared);
        this.saveCase(firstDebtor, shared);
        this.saveCase(secondDebtor, shared);
        DebtEntity other = this.saveDebt("200.00");
        this.saveCase(UUID.randomUUID(), other);

        List<ExonerationCaseRepository.DebtorRow> rows =
                this.exonerationCaseRepository.findDebtorRowsByDebtIds(List.of(shared.getId()));

        assertThat(rows).extracting(ExonerationCaseRepository.DebtorRow::getDebtId).containsOnly(shared.getId());
        assertThat(rows).extracting(ExonerationCaseRepository.DebtorRow::getUserId)
                .containsExactlyInAnyOrder(firstDebtor, secondDebtor);
    }

    private DebtEntity saveDebt(String amount) {
        return this.debtRepository.save(DebtEntity.builder().id(UUID.randomUUID())
                .contractNumber("RIT-" + UUID.randomUUID()).issueDate(LocalDate.of(2024, 1, 1))
                .creditorName("RIT creditor").amount(new BigDecimal(amount))
                .type(CreditorType.PRIVATE).guarantee(false).build());
    }

    private void saveCase(UUID userId, DebtEntity debt) {
        this.exonerationCaseRepository.saveAndFlush(ExonerationCaseEntity.builder().id(UUID.randomUUID())
                .caseNumber("RIT-CASE-" + UUID.randomUUID()).filingDate(LocalDate.of(2025, 1, 1))
                .debts(new ArrayList<>(List.of(debt))).userId(userId).build());
    }
}
