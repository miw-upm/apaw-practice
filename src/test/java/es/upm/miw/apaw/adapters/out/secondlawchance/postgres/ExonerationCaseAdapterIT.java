package es.upm.miw.apaw.adapters.out.secondlawchance.postgres;

import es.upm.miw.apaw.domain.model.secondlawchance.CreditorType;
import es.upm.miw.apaw.domain.model.secondlawchance.ExonerationCase;
import es.upm.miw.apaw.domain.model.secondlawchance.ExonerationCaseFindCriteria;
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

import static es.upm.miw.apaw.config.seeders.SecondLawChanceSeederForDev.CASE_ID_0;
import static es.upm.miw.apaw.config.seeders.SecondLawChanceSeederForDev.CASE_ID_1;
import static es.upm.miw.apaw.config.seeders.SecondLawChanceSeederForDev.USER_ID_0;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ExonerationCaseAdapterIT {
    @Autowired
    private ExonerationCaseAdapter exonerationCaseAdapter;
    @Autowired
    private ExonerationCaseRepository exonerationCaseRepository;
    @Autowired
    private DebtRepository debtRepository;

    @Test
    void testFindWithoutCriteria() {
        ExonerationCaseEntity own = this.saveCase(this.lawyer(), null, this.saveDebt(CreditorType.PRIVATE));

        List<ExonerationCase> cases = this.exonerationCaseAdapter.find(new ExonerationCaseFindCriteria());

        assertThat(cases).extracting(ExonerationCase::getId).contains(CASE_ID_0, CASE_ID_1, own.getId());
        assertThat(cases).extracting(ExonerationCase::getCaseNumber).isSortedAccordingTo(Comparator.naturalOrder());
    }

    @Test
    void testFindBlankLawyerDoesNotFilter() {
        List<ExonerationCase> cases = this.exonerationCaseAdapter.find(
                ExonerationCaseFindCriteria.builder().lawyer("  ").build());

        assertThat(cases).extracting(ExonerationCase::getId).contains(CASE_ID_0, CASE_ID_1);
    }

    @Test
    void testFindByLawyerIgnoringCase() {
        String lawyer = this.lawyer();
        ExonerationCaseEntity matching = this.saveCase(lawyer, null, this.saveDebt(CreditorType.PRIVATE));
        ExonerationCaseEntity other = this.saveCase(this.lawyer(), null, this.saveDebt(CreditorType.PRIVATE));

        List<ExonerationCase> cases = this.exonerationCaseAdapter.find(
                ExonerationCaseFindCriteria.builder().lawyer(lawyer.toUpperCase()).build());

        assertThat(cases).extracting(ExonerationCase::getId)
                .containsExactly(matching.getId())
                .doesNotContain(other.getId());
    }

    @Test
    void testFindByLawyerIsExactMatch() {
        String lawyer = this.lawyer();
        this.saveCase(lawyer, null, this.saveDebt(CreditorType.PRIVATE));

        List<ExonerationCase> cases = this.exonerationCaseAdapter.find(
                ExonerationCaseFindCriteria.builder().lawyer(lawyer.substring(0, lawyer.length() - 1)).build());

        assertThat(cases).isEmpty();
    }

    @Test
    void testFindByOpened() {
        String lawyer = this.lawyer();
        DebtEntity debt = this.saveDebt(CreditorType.PRIVATE);
        ExonerationCaseEntity open = this.saveCase(lawyer, null, debt);
        ExonerationCaseEntity resolved = this.saveCase(lawyer, LocalDate.of(2025, 6, 1), debt);

        assertThat(this.exonerationCaseAdapter.find(
                ExonerationCaseFindCriteria.builder().lawyer(lawyer).opened(true).build()))
                .extracting(ExonerationCase::getId).containsExactly(open.getId());
        assertThat(this.exonerationCaseAdapter.find(
                ExonerationCaseFindCriteria.builder().lawyer(lawyer).opened(false).build()))
                .extracting(ExonerationCase::getId).containsExactly(resolved.getId());
    }

    @Test
    void testFindByCreditorTypeWithoutDuplicates() {
        String lawyer = this.lawyer();
        DebtEntity firstPublic = this.saveDebt(CreditorType.PUBLIC);
        DebtEntity secondPublic = this.saveDebt(CreditorType.PUBLIC);
        DebtEntity privateDebt = this.saveDebt(CreditorType.PRIVATE);
        ExonerationCaseEntity twoPublic = this.saveCase(lawyer, null, firstPublic, secondPublic);
        ExonerationCaseEntity mixed = this.saveCase(lawyer, null, firstPublic, privateDebt);
        ExonerationCaseEntity onlyPrivate = this.saveCase(lawyer, null, privateDebt);

        List<ExonerationCase> cases = this.exonerationCaseAdapter.find(ExonerationCaseFindCriteria.builder()
                .lawyer(lawyer).creditorType(CreditorType.PUBLIC).build());

        assertThat(cases).extracting(ExonerationCase::getId)
                .containsExactlyInAnyOrder(twoPublic.getId(), mixed.getId())
                .doesNotContain(onlyPrivate.getId());
    }

    @Test
    void testFindCombiningCriteria() {
        String lawyer = this.lawyer();
        DebtEntity publicDebt = this.saveDebt(CreditorType.PUBLIC);
        DebtEntity privateDebt = this.saveDebt(CreditorType.PRIVATE);
        ExonerationCaseEntity target = this.saveCase(lawyer, null, publicDebt);
        this.saveCase(lawyer, LocalDate.of(2025, 6, 1), publicDebt);
        this.saveCase(lawyer, null, privateDebt);

        List<ExonerationCase> cases = this.exonerationCaseAdapter.find(ExonerationCaseFindCriteria.builder()
                .lawyer(lawyer).opened(true).creditorType(CreditorType.PUBLIC).build());

        assertThat(cases).extracting(ExonerationCase::getId).containsExactly(target.getId());
    }

    @Test
    void testFindReturnsSummaryWithoutDebts() {
        String lawyer = this.lawyer();
        UUID userId = UUID.randomUUID();
        this.saveCase(lawyer, null, userId, this.saveDebt(CreditorType.PUBLIC));

        List<ExonerationCase> cases = this.exonerationCaseAdapter.find(
                ExonerationCaseFindCriteria.builder().lawyer(lawyer).build());

        assertThat(cases).singleElement().satisfies(exonerationCase -> {
            assertThat(exonerationCase.getDebts()).isNull();
            assertThat(exonerationCase.getUserSnapshot().getId()).isEqualTo(userId);
            assertThat(exonerationCase.getUserSnapshot().getMobile()).isNull();
            assertThat(exonerationCase.getLawyer()).isEqualTo(lawyer);
        });
    }

    @Test
    void testFindSeederCases() {
        List<ExonerationCase> cases = this.exonerationCaseAdapter.find(
                ExonerationCaseFindCriteria.builder().opened(true).creditorType(CreditorType.PUBLIC).build());

        assertThat(cases).filteredOn(exonerationCase -> exonerationCase.getId().equals(CASE_ID_0))
                .singleElement()
                .satisfies(exonerationCase -> assertThat(exonerationCase.getUserSnapshot().getId())
                        .isEqualTo(USER_ID_0));
        assertThat(cases).extracting(ExonerationCase::getId).doesNotContain(CASE_ID_1);
    }

    private String lawyer() {
        return "Lawyer " + UUID.randomUUID();
    }

    private DebtEntity saveDebt(CreditorType type) {
        return this.debtRepository.save(DebtEntity.builder().id(UUID.randomUUID())
                .contractNumber("AIT-" + UUID.randomUUID()).issueDate(LocalDate.of(2024, 1, 1))
                .creditorName("AIT creditor").amount(new BigDecimal("100.00"))
                .type(type).guarantee(false).build());
    }

    private ExonerationCaseEntity saveCase(String lawyer, LocalDate resolutionDate, DebtEntity... debts) {
        return this.saveCase(lawyer, resolutionDate, UUID.randomUUID(), debts);
    }

    private ExonerationCaseEntity saveCase(String lawyer, LocalDate resolutionDate, UUID userId,
                                           DebtEntity... debts) {
        return this.exonerationCaseRepository.saveAndFlush(ExonerationCaseEntity.builder().id(UUID.randomUUID())
                .caseNumber("AIT-CASE-" + UUID.randomUUID()).filingDate(LocalDate.of(2025, 1, 1))
                .resolutionDate(resolutionDate).lawyer(lawyer)
                .debts(new ArrayList<>(List.of(debts))).userId(userId).build());
    }
}
