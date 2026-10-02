package es.upm.miw.apaw.adapters.out.courthearing.postgres;

import es.upm.miw.apaw.domain.model.courthearing.CourtHearingByCourtReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Comparator;
import java.util.List;

import static es.upm.miw.apaw.config.seeders.CourtHearingSeederForDev.COURT_0;
import static es.upm.miw.apaw.config.seeders.CourtHearingSeederForDev.COURT_1;
import static es.upm.miw.apaw.config.seeders.CourtHearingSeederForDev.COURT_2;
import static es.upm.miw.apaw.config.seeders.CourtHearingSeederForDev.COURT_4;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class CourtHearingRepositoryIT {
    @Autowired
    private CourtHearingRepository courtHearingRepository;

    @Test
    void testFindCourtUsageReport() {
        List<CourtHearingByCourtReport> report = this.courtHearingRepository.findHearingByCourtReport();

        assertThat(report).extracting(CourtHearingByCourtReport::getTotalHearingCount)
                .isSortedAccordingTo(Comparator.reverseOrder());
        assertThat(report).filteredOn(item -> item.getCourtName().equals(COURT_0.getName()))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getTotalHearingCount()).isGreaterThanOrEqualTo(3);
                    assertThat(item.getScheduledHearingCount()).isGreaterThanOrEqualTo(2);
                    assertThat(item.getScheduledHearingCount()).isLessThanOrEqualTo(item.getTotalHearingCount());
                });
        assertThat(report).filteredOn(item -> item.getCourtName().equals(COURT_1.getName()))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getTotalHearingCount()).isGreaterThanOrEqualTo(2);
                    assertThat(item.getScheduledHearingCount()).isGreaterThanOrEqualTo(1);
                    assertThat(item.getScheduledHearingCount()).isLessThan(item.getTotalHearingCount());
                });
        assertThat(report).filteredOn(item -> item.getCourtName().equals(COURT_2.getName()))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getTotalHearingCount()).isGreaterThanOrEqualTo(1);
                    assertThat(item.getScheduledHearingCount()).isLessThan(item.getTotalHearingCount());
                });
    }

    @Test
    void testFindCourtUsageReportExcludesCourtsWithoutHearings() {
        List<CourtHearingByCourtReport> report = this.courtHearingRepository.findHearingByCourtReport();

        assertThat(report).extracting(CourtHearingByCourtReport::getCourtName)
                .doesNotContain(COURT_4.getName());
    }
}