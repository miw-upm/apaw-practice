package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Comparator;
import java.util.List;

import static es.upm.miw.apaw.config.seeders.ExpertDirectoryServicesSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ExpertServiceScheduleRepositoryIT {
    @Autowired
    private ExpertServiceScheduleRepository expertServiceScheduleRepository;

    @Test
    void testExistsByTariffCode() {
        assertThat(this.expertServiceScheduleRepository.existsByTariffCode(SCHEDULE_0.getTariffCode())).isTrue();
        assertThat(this.expertServiceScheduleRepository.existsByTariffCode("NOT-A-TARIFF")).isFalse();
    }

    @Test
    void testExistsByLegalExpertProfilesIdIn() {
        assertThat(this.expertServiceScheduleRepository
                .existsByLegalExpertProfilesIdIn(List.of(PROFILE_ID_0, PROFILE_ID_2))).isTrue();
        assertThat(this.expertServiceScheduleRepository
                .existsByLegalExpertProfilesIdIn(List.of(PROFILE_ID_2))).isFalse();
    }

    @Test
    void testFindSpecialtyReportRows() {
        List<SpecialtyReportRow> rows = this.expertServiceScheduleRepository.findSpecialtyReportRows();

        assertThat(rows).extracting(SpecialtyReportRow::totalSchedules)
                .isSortedAccordingTo(Comparator.reverseOrder());
        assertThat(rows).filteredOn(row -> row.specialtyArea().equals(PROFILE_0.getSpecialtyArea()))
                .singleElement()
                .satisfies(row -> {
                    assertThat(row.totalProfiles()).isGreaterThanOrEqualTo(2);
                    assertThat(row.totalSchedules()).isGreaterThanOrEqualTo(2);
                    assertThat(row.averageRateAmount()).isBetween(100.0, 200.0);
                    assertThat(row.averageYearsOfExperience()).isBetween(12.0, 20.0);
                    assertThat(row.mostVeteranUserId()).isEqualTo(PROFILE_3.getUserSnapshot().getId());
                });
        assertThat(rows).filteredOn(row -> row.specialtyArea().equals(PROFILE_1.getSpecialtyArea()))
                .singleElement()
                .satisfies(row -> {
                    assertThat(row.totalProfiles()).isGreaterThanOrEqualTo(1);
                    assertThat(row.totalSchedules()).isGreaterThanOrEqualTo(1);
                    assertThat(row.mostVeteranUserId()).isEqualTo(PROFILE_1.getUserSnapshot().getId());
                });
    }

    @Test
    void testFindSpecialtyReportRowsExcludesProfilesWithoutSchedule() {
        List<SpecialtyReportRow> rows = this.expertServiceScheduleRepository.findSpecialtyReportRows();

        assertThat(rows).extracting(SpecialtyReportRow::specialtyArea)
                .doesNotContain(PROFILE_2.getSpecialtyArea());
    }
}
