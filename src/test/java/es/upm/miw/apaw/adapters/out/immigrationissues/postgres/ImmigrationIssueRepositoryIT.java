package es.upm.miw.apaw.adapters.out.immigrationissues.postgres;

import es.upm.miw.apaw.domain.model.immigrationissues.LawBasisUsageReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ImmigrationIssueRepositoryIT {

    @Autowired
    private ImmigrationIssueRepository immigrationIssueRepository;

    @Test
    void testFindLawBasisUsageReportContainsSeededGroups() {
        List<LawBasisUsageReport> reports = this.immigrationIssueRepository.findLawBasisUsageReport();

        assertThat(this.keys(reports)).contains(
                "Permiso en vigor|ES-LB-001",
                "Permiso en vigor|ES-LB-002",
                "Solicitud presentada|ES-LB-005");
    }

    @Test
    void testFindLawBasisUsageReportKeepsIssuesWithoutImmigrationStatus() {
        List<LawBasisUsageReport> reports = this.immigrationIssueRepository.findLawBasisUsageReport();

        assertThat(this.keys(reports)).contains(
                "null|ES-LB-003",
                "null|ES-LB-004");
    }

    @Test
    void testFindLawBasisUsageReportGroupsPerStatusAndLawCode() {
        List<LawBasisUsageReport> reports = this.immigrationIssueRepository.findLawBasisUsageReport();

        assertThat(this.keys(reports)).doesNotHaveDuplicates();
        assertThat(reports).allSatisfy(report ->
                assertThat(report.getTotalIssues()).isGreaterThanOrEqualTo(1));
    }

    @Test
    void testFindLawBasisUsageReportIsOrderedByTotalIssuesDescending() {
        List<LawBasisUsageReport> reports = this.immigrationIssueRepository.findLawBasisUsageReport();

        assertThat(reports).isSortedAccordingTo(
                java.util.Comparator.comparingLong(LawBasisUsageReport::getTotalIssues).reversed());
    }

    private List<String> keys(List<LawBasisUsageReport> reports) {
        return reports.stream()
                .map(report -> report.getClientImmigrationStatus() + "|" + report.getLawCode())
                .toList();
    }
}