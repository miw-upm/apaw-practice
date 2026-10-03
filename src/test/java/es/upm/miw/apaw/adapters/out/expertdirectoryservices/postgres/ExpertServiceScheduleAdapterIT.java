package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import es.upm.miw.apaw.domain.model.expertdirectoryservices.ExpertServiceSchedule;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.criteria.ExpertServiceScheduleFindCriteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.ExpertDirectoryServicesSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ExpertServiceScheduleAdapterIT {
    @Autowired
    private ExpertServiceScheduleAdapter expertServiceScheduleAdapter;
    @Autowired
    private ExpertServiceScheduleRepository expertServiceScheduleRepository;
    @Autowired
    private LegalExpertProfileRepository legalExpertProfileRepository;

    @Test
    void testFindWithoutCriteriaLoadsProfilesSortedByTariffCode() {
        List<ExpertServiceSchedule> schedules = this.expertServiceScheduleAdapter
                .find(new ExpertServiceScheduleFindCriteria());

        assertThat(schedules).extracting(ExpertServiceSchedule::getTariffCode)
                .contains(SCHEDULE_0.getTariffCode(), SCHEDULE_1.getTariffCode())
                .isSortedAccordingTo(Comparator.naturalOrder());
        assertThat(schedules).filteredOn(schedule -> schedule.getId().equals(SCHEDULE_ID_0))
                .singleElement()
                .satisfies(schedule -> assertThat(schedule.getLegalExpertProfiles())
                        .extracting(LegalExpertProfile::getId)
                        .containsExactlyInAnyOrder(PROFILE_ID_0, PROFILE_ID_1));
    }

    @Test
    void testFindKeepsOnlyUserIdInProfiles() {
        List<ExpertServiceSchedule> schedules = this.expertServiceScheduleAdapter
                .find(new ExpertServiceScheduleFindCriteria());

        assertThat(schedules).filteredOn(schedule -> schedule.getId().equals(SCHEDULE_ID_1))
                .singleElement()
                .satisfies(schedule -> assertThat(schedule.getLegalExpertProfiles()).singleElement()
                        .satisfies(profile -> {
                            assertThat(profile.getUserSnapshot().getId())
                                    .isEqualTo(PROFILE_3.getUserSnapshot().getId());
                            assertThat(profile.getUserSnapshot().getEmail()).isNull();
                        }));
    }

    @Test
    void testFindByMinRateAmountIsInclusive() {
        List<ExpertServiceSchedule> schedules = this.expertServiceScheduleAdapter.find(
                ExpertServiceScheduleFindCriteria.builder().minRateAmount(new BigDecimal("200.00")).build());

        assertThat(schedules).extracting(ExpertServiceSchedule::getId)
                .contains(SCHEDULE_ID_1).doesNotContain(SCHEDULE_ID_0);
    }

    @Test
    void testFindByWithSpecialCondition() {
        assertThat(this.expertServiceScheduleAdapter
                .find(ExpertServiceScheduleFindCriteria.builder().withSpecialCondition(true).build()))
                .extracting(ExpertServiceSchedule::getId).contains(SCHEDULE_ID_1).doesNotContain(SCHEDULE_ID_0);
        assertThat(this.expertServiceScheduleAdapter
                .find(ExpertServiceScheduleFindCriteria.builder().withSpecialCondition(false).build()))
                .extracting(ExpertServiceSchedule::getId).contains(SCHEDULE_ID_0).doesNotContain(SCHEDULE_ID_1);
    }

    @Test
    void testFindBySpecialtyAreaDoesNotDuplicateSchedules() {
        String specialtyArea = "Distinct law " + UUID.randomUUID();
        UUID scheduleId = this.createScheduleWithTwoProfiles(specialtyArea);

        List<ExpertServiceSchedule> schedules = this.expertServiceScheduleAdapter.find(
                ExpertServiceScheduleFindCriteria.builder().specialtyArea(specialtyArea).build());

        assertThat(schedules).singleElement().satisfies(schedule -> {
            assertThat(schedule.getId()).isEqualTo(scheduleId);
            assertThat(schedule.getLegalExpertProfiles()).hasSize(2);
        });
    }

    @Test
    void testFindBySpecialtyAreaWithoutMatch() {
        assertThat(this.expertServiceScheduleAdapter.find(
                ExpertServiceScheduleFindCriteria.builder().specialtyArea("Unknown law").build())).isEmpty();
    }

    private UUID createScheduleWithTwoProfiles(String specialtyArea) {
        List<LegalExpertProfileEntity> profiles = new ArrayList<>(this.legalExpertProfileRepository.saveAll(
                List.of(this.newProfileEntity(specialtyArea), this.newProfileEntity(specialtyArea))));
        return this.expertServiceScheduleRepository.saveAndFlush(ExpertServiceScheduleEntity.builder()
                .id(UUID.randomUUID())
                .tariffCode("TC-ADAPTER-" + UUID.randomUUID())
                .description("Adapter IT")
                .rateAmount(new BigDecimal("75.00"))
                .currency("EUR")
                .creationDate(LocalDate.now())
                .legalExpertProfiles(profiles)
                .build()).getId();
    }

    private LegalExpertProfileEntity newProfileEntity(String specialtyArea) {
        return LegalExpertProfileEntity.builder()
                .id(UUID.randomUUID())
                .taxIdCode("TAX-" + UUID.randomUUID())
                .specialtyArea(specialtyArea)
                .yearsOfExperience(4)
                .requiresPrepayment(false)
                .partnershipDate(LocalDate.now())
                .userId(UUID.randomUUID())
                .build();
    }
}
