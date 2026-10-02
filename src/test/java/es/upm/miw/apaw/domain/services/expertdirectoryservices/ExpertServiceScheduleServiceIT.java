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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
