package es.upm.miw.apaw.functionaltests.euregulation;

import es.upm.miw.apaw.config.seeders.ComplianceAssessmentSeederForDev;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.euregulation.ApplicationArea;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceByAreaReport;
import es.upm.miw.apaw.domain.model.euregulation.LawyerProductivityReport;
import es.upm.miw.apaw.domain.model.euregulation.OverdueComplianceReport;
import es.upm.miw.apaw.domain.model.euregulation.RiskExposureReport;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ComplianceReportsResourceFT {
    private static final UUID USER_ID = ComplianceAssessmentSeederForDev.USER_ID;
    private static final UUID USER_ID_1 = ComplianceAssessmentSeederForDev.USER_ID_1;

    @LocalServerPort
    private int port;

    @MockitoBean
    private UserFinder userFinder;

    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port)
                .build();
        UserSnapshot user0 = UserSnapshot.builder()
                .id(USER_ID)
                .mobile("600000100")
                .firstName("cliente0")
                .build();
        UserSnapshot user1 = UserSnapshot.builder()
                .id(USER_ID_1)
                .mobile("600000101")
                .firstName("cliente1")
                .build();
        when(this.userFinder.findByIds(Set.of(USER_ID, USER_ID_1))).thenReturn(List.of(user0, user1));
    }

    @Test
    void testRiskExposureReportAggregatesByUserAndHydratesUsers() {
        List<RiskExposureReport> reports = this.getReports(
                "/reports/risk-exposure", RiskExposureReport[].class);

        assertThat(reports)
                .isSortedAccordingTo(Comparator.comparingLong(RiskExposureReport::getRiskScore).reversed());
        RiskExposureReport user0Report = this.findRiskReport(reports, USER_ID);
        RiskExposureReport user1Report = this.findRiskReport(reports, USER_ID_1);
        assertThat(user0Report)
                .satisfies(report -> {
                    assertThat(report.getUserSnapshotId()).isEqualTo(USER_ID);
                    assertThat(report.getFirstName()).isEqualTo("cliente0");
                    assertThat(report.getMobile()).isEqualTo("600000100");
                    assertThat(report.getTotalAssessments()).isGreaterThanOrEqualTo(3);
                    assertThat(report.getHighRiskCount()).isGreaterThanOrEqualTo(1);
                    assertThat(report.getMediumRiskCount()).isGreaterThanOrEqualTo(1);
                    assertThat(report.getLowRiskCount()).isGreaterThanOrEqualTo(1);
                    assertThat(report.getNonCompliantCount()).isZero();
                    assertThat(report.getRiskScore()).isGreaterThanOrEqualTo(6);
                });
        assertThat(user1Report)
                .satisfies(report -> {
                    assertThat(report.getUserSnapshotId()).isEqualTo(USER_ID_1);
                    assertThat(report.getFirstName()).isEqualTo("cliente1");
                    assertThat(report.getTotalAssessments()).isEqualTo(2);
                    assertThat(report.getHighRiskCount()).isEqualTo(1);
                    assertThat(report.getLowRiskCount()).isEqualTo(1);
                    assertThat(report.getNonCompliantCount()).isEqualTo(1);
                    assertThat(report.getRiskScore()).isEqualTo(4);
                });
    }

    @Test
    void testComplianceByAreaReportHasDistinctRatesAndSortedOrder() {
        List<ComplianceByAreaReport> reports = this.getReports(
                "/reports/compliance-by-area", ComplianceByAreaReport[].class);

        assertThat(reports)
                .isSortedAccordingTo(Comparator.comparing(ComplianceByAreaReport::getComplianceRate));
        ComplianceByAreaReport dataProtection = this.findArea(reports, ApplicationArea.DATA_PROTECTION);
        assertThat(dataProtection.getTotalAssessments()).isGreaterThanOrEqualTo(3);
        assertThat(dataProtection.getCompliantCount()).isGreaterThanOrEqualTo(1);
        assertThat(dataProtection.getPartiallyCompliantCount()).isGreaterThanOrEqualTo(1);
        assertThat(dataProtection.getNonCompliantCount()).isGreaterThanOrEqualTo(1);
        assertThat(dataProtection.getComplianceRate()).isGreaterThan(BigDecimal.ZERO)
                .isLessThan(BigDecimal.ONE);

        ComplianceByAreaReport digitalTechnology = this.findArea(reports, ApplicationArea.DIGITAL_TECHNOLOGY);
        assertThat(digitalTechnology.getTotalAssessments()).isGreaterThanOrEqualTo(2);
        assertThat(digitalTechnology.getCompliantCount()).isGreaterThanOrEqualTo(1);
        assertThat(digitalTechnology.getPendingReviewCount()).isGreaterThanOrEqualTo(1);
        assertThat(digitalTechnology.getComplianceRate()).isGreaterThan(BigDecimal.ZERO)
                .isLessThan(BigDecimal.ONE);
    }

    @Test
    void testComplianceDeadlinesReportCountsOverdueAndDueSoonAndHydratesUsers() {
        List<OverdueComplianceReport> reports = this.getReports(
                "/reports/compliance-deadlines", OverdueComplianceReport[].class);

        assertThat(reports)
                .isSortedAccordingTo(Comparator.comparingLong(OverdueComplianceReport::getOverdueCount).reversed());
        OverdueComplianceReport user0Report = this.findDeadlineReport(reports, USER_ID);
        OverdueComplianceReport user1Report = this.findDeadlineReport(reports, USER_ID_1);
        assertThat(user0Report)
                .satisfies(report -> {
                    assertThat(report.getUserSnapshotId()).isEqualTo(USER_ID);
                    assertThat(report.getFirstName()).isEqualTo("cliente0");
                    assertThat(report.getMobile()).isEqualTo("600000100");
                    assertThat(report.getTotalAssessments()).isGreaterThanOrEqualTo(2);
                    assertThat(report.getOverdueCount()).isGreaterThanOrEqualTo(1);
                    assertThat(report.getDueSoonCount()).isGreaterThanOrEqualTo(1);
                    assertThat(report.getNearestDeadline()).isEqualTo(LocalDate.of(2025, 6, 30));
                    assertThat(report.getDaysToNearestDeadline()).isEqualTo(
                            (int) ChronoUnit.DAYS.between(LocalDate.now(), report.getNearestDeadline()));
                });
        assertThat(user1Report)
                .satisfies(report -> {
                    assertThat(report.getUserSnapshotId()).isEqualTo(USER_ID_1);
                    assertThat(report.getFirstName()).isEqualTo("cliente1");
                    assertThat(report.getTotalAssessments()).isEqualTo(1);
                    assertThat(report.getOverdueCount()).isZero();
                    assertThat(report.getDueSoonCount()).isEqualTo(1);
                    assertThat(report.getNearestDeadline()).isEqualTo(LocalDate.now().plusDays(5));
                    assertThat(report.getDaysToNearestDeadline()).isEqualTo(5);
                });
    }

    @Test
    void testLawyerProductivityReportCountsAiAndManualAndSortsByTotal() {
        List<LawyerProductivityReport> reports = this.getReports(
                "/reports/lawyer-productivity", LawyerProductivityReport[].class);

        assertThat(reports)
                .isSortedAccordingTo(Comparator
                        .comparingLong(LawyerProductivityReport::getTotalAssessments).reversed());
        LawyerProductivityReport miguel = this.findLawyerReport(reports, "Miguel Torres");
        assertThat(miguel.getTotalAssessments()).isEqualTo(3);
        assertThat(miguel.getAiGeneratedCount()).isEqualTo(2);
        assertThat(miguel.getManualCount()).isEqualTo(1);
        assertThat(miguel.getAiRatio()).isCloseTo(
                BigDecimal.valueOf(2).divide(BigDecimal.valueOf(3), 4, RoundingMode.HALF_UP),
                within(new BigDecimal("0.0001")));

        LawyerProductivityReport laura = this.findLawyerReport(reports, "Laura García");
        assertThat(laura.getTotalAssessments()).isEqualTo(2);
        assertThat(laura.getAiGeneratedCount()).isEqualTo(1);
        assertThat(laura.getManualCount()).isEqualTo(1);
        assertThat(laura.getAiRatio()).isEqualByComparingTo("0.5");
    }

    private RiskExposureReport findRiskReport(List<RiskExposureReport> reports, UUID userId) {
        return reports.stream()
                .filter(report -> report.getUserSnapshotId().equals(userId))
                .findFirst()
                .orElseThrow();
    }

    private OverdueComplianceReport findDeadlineReport(List<OverdueComplianceReport> reports, UUID userId) {
        return reports.stream()
                .filter(report -> report.getUserSnapshotId().equals(userId))
                .findFirst()
                .orElseThrow();
    }

    private LawyerProductivityReport findLawyerReport(List<LawyerProductivityReport> reports, String lawyer) {
        return reports.stream()
                .filter(report -> lawyer.equals(report.getResponsibleLawyer()))
                .findFirst()
                .orElseThrow();
    }

    private ComplianceByAreaReport findArea(
            List<ComplianceByAreaReport> reports, ApplicationArea applicationArea) {
        return reports.stream()
                .filter(report -> report.getApplicationArea() == applicationArea)
                .findFirst()
                .orElseThrow();
    }

    private <T> List<T> getReports(String path, Class<T[]> responseType) {
        T[] reports = this.restTestClient.get()
                .uri(path)
                .exchange()
                .expectStatus().isOk()
                .expectBody(responseType)
                .returnResult()
                .getResponseBody();
        return reports == null ? List.of() : Arrays.asList(reports);
    }
}
