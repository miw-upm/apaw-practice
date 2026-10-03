package es.upm.miw.apaw.domain.services.expertdirectoryservices;

import es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres.ExpertServiceScheduleEntity;
import es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres.ExpertServiceScheduleRepository;
import es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres.LegalExpertProfileEntity;
import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.ExpertServiceSchedule;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.CreationExpertServiceSchedule;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.criteria.ExpertServiceScheduleFindCriteria;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.ExpertDirectoryServicesSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ExpertServiceScheduleServiceIT {

    @Autowired
    private ExpertServiceScheduleService expertServiceScheduleService;

    @Autowired
    private LegalExpertProfileService legalExpertProfileService;

    @Autowired
    private ExpertServiceScheduleRepository expertServiceScheduleRepository;

    @MockitoBean
    private UserFinder userFinder;

    @BeforeEach
    void setUp() {
        when(this.userFinder.read(any(UUID.class))).thenAnswer(invocation ->
                UserSnapshot.builder().id(invocation.getArgument(0)).build());
        when(this.userFinder.findByIds(anySet())).thenAnswer(invocation -> {
            Set<UUID> ids = invocation.getArgument(0);
            return ids.stream().map(id -> UserSnapshot.builder().id(id).firstName("Hydrated")
                    .email(this.emailOf(id)).build()).toList();
        });
    }

    @Test
    void testCreate() {
        UUID profileId1 = this.createProfile().getId();
        UUID profileId2 = this.createProfile().getId();
        CreationExpertServiceSchedule creation = this.creation(List.of(profileId1, profileId2));

        ExpertServiceSchedule schedule = this.expertServiceScheduleService.create(creation);

        assertThat(schedule.getId()).isNotNull();
        assertThat(schedule.getCreationDate()).isEqualTo(LocalDate.now());
        assertThat(schedule.getLegalExpertProfiles()).extracting(LegalExpertProfile::getId)
                .containsExactly(profileId1, profileId2);

        ExpertServiceScheduleEntity entity = this.expertServiceScheduleRepository.findById(schedule.getId())
                .orElseThrow();
        assertThat(entity.getTariffCode()).isEqualTo(creation.getTariffCode());
        assertThat(entity.getLegalExpertProfiles()).extracting(LegalExpertProfileEntity::getId)
                .containsExactlyInAnyOrder(profileId1, profileId2);
    }

    @Test
    void testCreateDefaultCurrencyAndNoProfiles() {
        CreationExpertServiceSchedule creation = this.creation(null);

        ExpertServiceSchedule schedule = this.expertServiceScheduleService.create(creation);

        assertThat(schedule.getCurrency()).isEqualTo("EUR");
        assertThat(schedule.getLegalExpertProfiles()).isEmpty();
    }

    @Test
    void testCreateTariffCodeConflict() {
        CreationExpertServiceSchedule creation = this.creation(List.of());
        this.expertServiceScheduleService.create(creation);

        assertThatThrownBy(() -> this.expertServiceScheduleService.create(creation))
                .isInstanceOf(ConflictException.class);
    }

    @Test
    void testCreateProfileNotFound() {
        CreationExpertServiceSchedule creation = this.creation(List.of(UUID.randomUUID()));

        assertThatThrownBy(() -> this.expertServiceScheduleService.create(creation))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testCreateDuplicatedProfileIds() {
        UUID profileId = this.createProfile().getId();
        CreationExpertServiceSchedule creation = this.creation(List.of(profileId, profileId));

        assertThatThrownBy(() -> this.expertServiceScheduleService.create(creation))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    void testCreateProfileAlreadyAssigned() {
        UUID profileId = this.createProfile().getId();
        this.expertServiceScheduleService.create(this.creation(List.of(profileId)));
        CreationExpertServiceSchedule other = this.creation(List.of(profileId));

        assertThatThrownBy(() -> this.expertServiceScheduleService.create(other))
                .isInstanceOf(ConflictException.class);
    }

    @Test
    void testDeleteAssignedProfileConflict() {
        UUID profileId = this.createProfile().getId();
        this.expertServiceScheduleService.create(this.creation(List.of(profileId)));

        assertThatThrownBy(() -> this.legalExpertProfileService.delete(profileId.toString()))
                .isInstanceOf(ConflictException.class);
    }

    @Test
    void testFindWithoutCriteriaContainsSeededSortedByTariffCode() {
        List<ExpertServiceSchedule> schedules = this.expertServiceScheduleService
                .find(new ExpertServiceScheduleFindCriteria());

        assertThat(schedules).extracting(ExpertServiceSchedule::getTariffCode)
                .contains(SCHEDULE_0.getTariffCode(), SCHEDULE_1.getTariffCode())
                .isSortedAccordingTo(Comparator.naturalOrder());
        assertThat(schedules).filteredOn(schedule -> schedule.getTariffCode().equals(SCHEDULE_0.getTariffCode()))
                .singleElement()
                .satisfies(schedule -> assertThat(schedule.getLegalExpertProfiles())
                        .extracting(LegalExpertProfile::getId)
                        .containsExactlyInAnyOrder(PROFILE_ID_0, PROFILE_ID_1));
        verify(this.userFinder, times(1)).findByIds(anySet());
    }

    @Test
    void testFindHydratesUsersOfProfiles() {
        List<ExpertServiceSchedule> schedules = this.expertServiceScheduleService
                .find(new ExpertServiceScheduleFindCriteria());

        assertThat(schedules).filteredOn(schedule -> schedule.getTariffCode().equals(SCHEDULE_1.getTariffCode()))
                .singleElement()
                .satisfies(schedule -> assertThat(schedule.getLegalExpertProfiles()).singleElement()
                        .satisfies(profile -> {
                            UUID userId = PROFILE_3.getUserSnapshot().getId();
                            assertThat(profile.getUserSnapshot().getId()).isEqualTo(userId);
                            assertThat(profile.getUserSnapshot().getFirstName()).isEqualTo("Hydrated");
                            assertThat(profile.getUserSnapshot().getEmail()).isEqualTo(this.emailOf(userId));
                        }));
    }

    @Test
    void testFindByMinRateAmount() {
        List<String> tariffCodes = this.findTariffCodes(
                ExpertServiceScheduleFindCriteria.builder().minRateAmount(new BigDecimal("150")).build());

        assertThat(tariffCodes).contains(SCHEDULE_1.getTariffCode()).doesNotContain(SCHEDULE_0.getTariffCode());
    }

    @Test
    void testFindByWithSpecialCondition() {
        assertThat(this.findTariffCodes(ExpertServiceScheduleFindCriteria.builder().withSpecialCondition(true).build()))
                .contains(SCHEDULE_1.getTariffCode()).doesNotContain(SCHEDULE_0.getTariffCode());
        assertThat(this.findTariffCodes(ExpertServiceScheduleFindCriteria.builder().withSpecialCondition(false).build()))
                .contains(SCHEDULE_0.getTariffCode()).doesNotContain(SCHEDULE_1.getTariffCode());
    }

    @Test
    void testFindBySpecialtyAreaTraversesRelationAndKeepsAllProfiles() {
        List<ExpertServiceSchedule> schedules = this.expertServiceScheduleService.find(
                ExpertServiceScheduleFindCriteria.builder().specialtyArea(PROFILE_1.getSpecialtyArea()).build());

        assertThat(schedules).extracting(ExpertServiceSchedule::getTariffCode)
                .contains(SCHEDULE_0.getTariffCode()).doesNotContain(SCHEDULE_1.getTariffCode());
        assertThat(schedules).filteredOn(schedule -> schedule.getTariffCode().equals(SCHEDULE_0.getTariffCode()))
                .singleElement()
                .satisfies(schedule -> assertThat(schedule.getLegalExpertProfiles()).hasSize(2));
    }

    @Test
    void testFindByUserEmailIgnoringCase() {
        String email = this.emailOf(PROFILE_3.getUserSnapshot().getId()).toUpperCase();

        List<String> tariffCodes = this.findTariffCodes(
                ExpertServiceScheduleFindCriteria.builder().userEmail(email).build());

        assertThat(tariffCodes).contains(SCHEDULE_1.getTariffCode()).doesNotContain(SCHEDULE_0.getTariffCode());
        verify(this.userFinder, times(1)).findByIds(anySet());
    }

    @Test
    void testFindByUserEmailWithoutMatch() {
        assertThat(this.findTariffCodes(ExpertServiceScheduleFindCriteria.builder().userEmail("nobody@test.com").build()))
                .doesNotContain(SCHEDULE_0.getTariffCode(), SCHEDULE_1.getTariffCode());
    }

    @Test
    void testFindCombinedCriteria() {
        List<String> tariffCodes = this.findTariffCodes(ExpertServiceScheduleFindCriteria.builder()
                .minRateAmount(new BigDecimal("50"))
                .withSpecialCondition(false)
                .specialtyArea(PROFILE_0.getSpecialtyArea())
                .userEmail(this.emailOf(PROFILE_0.getUserSnapshot().getId()))
                .build());

        assertThat(tariffCodes).contains(SCHEDULE_0.getTariffCode()).doesNotContain(SCHEDULE_1.getTariffCode());
    }

    @Test
    void testFindBlankStringsDoNotFilter() {
        List<String> tariffCodes = this.findTariffCodes(
                ExpertServiceScheduleFindCriteria.builder().specialtyArea(" ").userEmail("").build());

        assertThat(tariffCodes).contains(SCHEDULE_0.getTariffCode(), SCHEDULE_1.getTariffCode());
    }

    @Test
    void testFindUserNotFound() {
        when(this.userFinder.findByIds(anySet())).thenReturn(List.of());
        ExpertServiceScheduleFindCriteria criteria = new ExpertServiceScheduleFindCriteria();

        assertThatThrownBy(() -> this.expertServiceScheduleService.find(criteria))
                .isInstanceOf(NotFoundException.class).hasMessageContaining("User id not found");
    }

    private List<String> findTariffCodes(ExpertServiceScheduleFindCriteria criteria) {
        return this.expertServiceScheduleService.find(criteria).stream()
                .map(ExpertServiceSchedule::getTariffCode)
                .toList();
    }

    private String emailOf(UUID userId) {
        return userId + "@test.com";
    }

    private CreationExpertServiceSchedule creation(List<UUID> profileIds) {
        return CreationExpertServiceSchedule.builder()
                .tariffCode("TC-TEST-" + UUID.randomUUID().toString().substring(0, 8))
                .description("Servicio pericial IT")
                .rateAmount(new BigDecimal("250.00"))
                .legalExpertProfileIds(profileIds)
                .build();
    }

    private LegalExpertProfile createProfile() {
        return this.legalExpertProfileService.create(LegalExpertProfile.builder()
                .taxIdCode("TAX-" + UUID.randomUUID().toString().substring(0, 8))
                .specialtyArea("Labour law")
                .yearsOfExperience(5)
                .userSnapshot(UserSnapshot.builder().id(UUID.randomUUID()).build())
                .build());
    }
}
