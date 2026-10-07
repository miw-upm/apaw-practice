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

        assertThat(report).extracting(DeadlineWorkloadReport::expiredDeadlineCount)
                .isSortedAccordingTo(Comparator.reverseOrder());
        assertThat(report).filteredOn(item -> item.userId().equals(USER_ID_0))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.expiredDeadlineCount()).isGreaterThanOrEqualTo(4);
                    assertThat(item.totalDeadlineCount()).isGreaterThanOrEqualTo(5);
                    assertThat(item.holidayAffectedDeadlineCount()).isGreaterThanOrEqualTo(2);
                    assertThat(item.expiredDeadlineCount()).isLessThanOrEqualTo(item.totalDeadlineCount());
                    assertThat(item.holidayAffectedDeadlineCount()).isLessThanOrEqualTo(item.totalDeadlineCount());
                });
        assertThat(report).filteredOn(item -> item.userId().equals(USER_ID_1))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.expiredDeadlineCount()).isGreaterThanOrEqualTo(2);
                    assertThat(item.totalDeadlineCount()).isGreaterThanOrEqualTo(3);
                    assertThat(item.holidayAffectedDeadlineCount()).isGreaterThanOrEqualTo(1);
                });
    }

    @Test
    void testFindWorkloadReportKeepsLawyersWithoutHolidays() {
        List<DeadlineWorkloadReport> report = this.deadlineRepository.findWorkloadReport(LocalDate.now());

        assertThat(report).filteredOn(item -> item.userId().equals(USER_ID_2))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.totalDeadlineCount()).isGreaterThanOrEqualTo(2);
                    assertThat(item.holidayAffectedDeadlineCount()).isZero();
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

        assertThat(report).extracting(DeadlineWorkloadReport::expiredDeadlineCount).containsOnly(0L);
    }
}
