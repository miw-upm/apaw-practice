package es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres;

import es.upm.miw.apaw.domain.model.deadlinecalculator.DeadlineWorkloadReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static es.upm.miw.apaw.config.seeders.DeadlineCalculatorSeederForDev.USER_ID_0;
import static es.upm.miw.apaw.config.seeders.DeadlineCalculatorSeederForDev.USER_ID_1;
import static es.upm.miw.apaw.config.seeders.DeadlineCalculatorSeederForDev.USER_ID_2;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class DeadlineRepositoryIT {
    @Autowired
    private DeadlineRepository deadlineRepository;

    @Test
    void testFindWorkloadReport() {
        List<DeadlineWorkloadReport> report = this.deadlineRepository.findWorkloadReport(LocalDate.now());

        assertThat(report).extracting(DeadlineWorkloadReport::expiredCount)
                .isSortedAccordingTo(Comparator.reverseOrder());
        assertThat(report).filteredOn(item -> item.userId().equals(USER_ID_0))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.expiredCount()).isGreaterThanOrEqualTo(4);
                    assertThat(item.deadlineCount()).isGreaterThanOrEqualTo(5);
                    assertThat(item.holidayAffectedCount()).isGreaterThanOrEqualTo(2);
                    assertThat(item.expiredCount()).isLessThanOrEqualTo(item.deadlineCount());
                    assertThat(item.holidayAffectedCount()).isLessThanOrEqualTo(item.deadlineCount());
                });
        assertThat(report).filteredOn(item -> item.userId().equals(USER_ID_1))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.expiredCount()).isGreaterThanOrEqualTo(2);
                    assertThat(item.deadlineCount()).isGreaterThanOrEqualTo(3);
                    assertThat(item.holidayAffectedCount()).isGreaterThanOrEqualTo(1);
                });
    }

    @Test
    void testFindWorkloadReportKeepsLawyersWithoutHolidays() {
        List<DeadlineWorkloadReport> report = this.deadlineRepository.findWorkloadReport(LocalDate.now());

        assertThat(report).filteredOn(item -> item.userId().equals(USER_ID_2))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.deadlineCount()).isGreaterThanOrEqualTo(2);
                    assertThat(item.holidayAffectedCount()).isZero();
                });
    }

    @Test
    void testFindWorkloadReportBreaksTiesByDeadlineCount() {
        List<DeadlineWorkloadReport> report = this.deadlineRepository.findWorkloadReport(LocalDate.now());

        assertThat(report).extracting(DeadlineWorkloadReport::userId)
                .containsSubsequence(USER_ID_0, USER_ID_1, USER_ID_2);
    }

    @Test
    void testFindWorkloadReportWithADateBeforeEveryDueDate() {
        List<DeadlineWorkloadReport> report = this.deadlineRepository.findWorkloadReport(LocalDate.of(2000, 1, 1));

        assertThat(report).extracting(DeadlineWorkloadReport::expiredCount).containsOnly(0L);
    }
}
