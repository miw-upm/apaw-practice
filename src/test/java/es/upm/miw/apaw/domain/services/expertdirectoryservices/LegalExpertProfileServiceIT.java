package es.upm.miw.apaw.domain.services.expertdirectoryservices;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.ExpertDirectoryServicesSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class LegalExpertProfileServiceIT {
    @Autowired
    private LegalExpertProfileService legalExpertProfileService;
    @MockitoBean
    private UserFinder userFinder;

    @Test
    void testRead() {
        LegalExpertProfile profile = this.legalExpertProfileService.read(PROFILE_ID_0.toString());

        assertThat(profile.getId()).isEqualTo(PROFILE_ID_0);
        assertThat(profile.getTaxIdCode()).isEqualTo(PROFILE_0.getTaxIdCode());
        assertThat(profile.getSpecialtyArea()).isEqualTo(PROFILE_0.getSpecialtyArea());
        assertThat(profile.getUserSnapshot().getId()).isEqualTo(PROFILE_0.getUserSnapshot().getId());
    }

    @Test
    void testReadNotFound() {
        String id = UUID.randomUUID().toString();

        assertThatThrownBy(() -> this.legalExpertProfileService.read(id))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id);
    }

    @Test
    void testFindAllContainsSeededSortedByTaxIdCode() {
        List<LegalExpertProfile> profiles = this.legalExpertProfileService.findAll().toList();

        assertThat(profiles).extracting(LegalExpertProfile::getId)
                .containsSubsequence(PROFILE_ID_0, PROFILE_ID_1, PROFILE_ID_2);
    }

    @Test
    void testCreate() {
        UserSnapshot user = this.mockUser();

        LegalExpertProfile created = this.legalExpertProfileService.create(this.newProfile(user.getId()));

        assertThat(created.getId()).isNotNull();
        assertThat(created.getRequiresPrepayment()).isFalse();
        assertThat(created.getPartnershipDate()).isNotNull();
        assertThat(created.getUserSnapshot().getId()).isEqualTo(user.getId());
    }

    @Test
    void testCreateDuplicateTaxIdCode() {
        LegalExpertProfile profile = this.newProfile(this.mockUser().getId());
        profile.setTaxIdCode(PROFILE_0.getTaxIdCode());

        assertThatThrownBy(() -> this.legalExpertProfileService.create(profile))
                .isInstanceOf(ConflictException.class).hasMessageContaining(PROFILE_0.getTaxIdCode());
    }

    @Test
    void testCreateDuplicateProfessionalLicense() {
        LegalExpertProfile profile = this.newProfile(this.mockUser().getId());
        profile.setProfessionalLicense(PROFILE_0.getProfessionalLicense());

        assertThatThrownBy(() -> this.legalExpertProfileService.create(profile))
                .isInstanceOf(ConflictException.class).hasMessageContaining(PROFILE_0.getProfessionalLicense());
    }

    @Test
    void testUpdate() {
        UserSnapshot user = this.mockUser();
        LegalExpertProfile created = this.legalExpertProfileService.create(this.newProfile(user.getId()));
        LegalExpertProfile update = this.newProfile(user.getId());
        update.setSpecialtyArea("Updated area");

        LegalExpertProfile updated = this.legalExpertProfileService.update(created.getId().toString(), update);

        assertThat(updated.getId()).isEqualTo(created.getId());
        assertThat(updated.getSpecialtyArea()).isEqualTo("Updated area");
    }

    @Test
    void testUpdateDuplicateTaxIdCode() {
        UserSnapshot user = this.mockUser();
        LegalExpertProfile created = this.legalExpertProfileService.create(this.newProfile(user.getId()));
        LegalExpertProfile update = this.newProfile(user.getId());
        update.setTaxIdCode(PROFILE_1.getTaxIdCode());

        assertThatThrownBy(() -> this.legalExpertProfileService.update(created.getId().toString(), update))
                .isInstanceOf(ConflictException.class);
    }

    @Test
    void testUpdatePartial() {
        UserSnapshot user = this.mockUser();
        LegalExpertProfile created = this.legalExpertProfileService.create(this.newProfile(user.getId()));

        this.legalExpertProfileService.updatePartial(List.of(LegalExpertProfile.builder()
                .id(created.getId()).yearsOfExperience(30).build()));

        LegalExpertProfile read = this.legalExpertProfileService.read(created.getId().toString());
        assertThat(read.getYearsOfExperience()).isEqualTo(30);
        assertThat(read.getTaxIdCode()).isEqualTo(created.getTaxIdCode());
    }

    @Test
    void testUpdatePartialDuplicatedIds() {
        List<LegalExpertProfile> updates = List.of(
                LegalExpertProfile.builder().id(PROFILE_ID_0).yearsOfExperience(1).build(),
                LegalExpertProfile.builder().id(PROFILE_ID_0).yearsOfExperience(2).build());

        assertThatThrownBy(() -> this.legalExpertProfileService.updatePartial(updates))
                .isInstanceOf(ConflictException.class);
    }

    @Test
    void testDelete() {
        UserSnapshot user = this.mockUser();
        LegalExpertProfile created = this.legalExpertProfileService.create(this.newProfile(user.getId()));

        this.legalExpertProfileService.delete(created.getId().toString());

        assertThatThrownBy(() -> this.legalExpertProfileService.read(created.getId().toString()))
                .isInstanceOf(NotFoundException.class);
    }

    private UserSnapshot mockUser() {
        UserSnapshot user = UserSnapshot.builder()
                .id(UUID.randomUUID()).mobile("600999999").firstName("Mock").build();
        when(this.userFinder.read(user.getId())).thenReturn(user);
        return user;
    }

    private LegalExpertProfile newProfile(UUID userId) {
        return LegalExpertProfile.builder()
                .taxIdCode("TAX-" + UUID.randomUUID())
                .professionalLicense("LIC-" + UUID.randomUUID())
                .specialtyArea("Criminal law")
                .yearsOfExperience(5)
                .userSnapshot(UserSnapshot.builder().id(userId).build())
                .build();
    }
}
