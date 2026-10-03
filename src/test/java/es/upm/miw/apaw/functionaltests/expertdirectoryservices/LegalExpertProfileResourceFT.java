package es.upm.miw.apaw.functionaltests.expertdirectoryservices;

import es.upm.miw.apaw.adapters.in.expertdirectoryservices.LegalExpertProfileResource;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.LegalExpertProfile;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.reports.LegalExpertProfileSpecialtyReport;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.ExpertDirectoryServicesSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class LegalExpertProfileResourceFT {
    @LocalServerPort
    private int port;
    private RestTestClient restTestClient;
    @MockitoBean
    private UserFinder userFinder;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port).build();
    }

    @Test
    void testRead() {
        this.restTestClient.get().uri(LegalExpertProfileResource.LEGAL_EXPERT_PROFILES + "/" + PROFILE_ID_1)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalExpertProfile.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(PROFILE_ID_1);
                    assertThat(body.getTaxIdCode()).isEqualTo(PROFILE_1.getTaxIdCode());
                    assertThat(body.getSpecialtyArea()).isEqualTo(PROFILE_1.getSpecialtyArea());
                    assertThat(body.getYearsOfExperience()).isEqualTo(PROFILE_1.getYearsOfExperience());
                });
    }

    @Test
    void testReadNotFound() {
        this.restTestClient.get().uri(LegalExpertProfileResource.LEGAL_EXPERT_PROFILES + "/" + UUID.randomUUID())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testFindAll() {
        this.restTestClient.get().uri(LegalExpertProfileResource.LEGAL_EXPERT_PROFILES)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalExpertProfile[].class)
                .value(body -> assertThat(body).extracting(LegalExpertProfile::getId)
                        .containsSubsequence(PROFILE_ID_0, PROFILE_ID_1, PROFILE_ID_2));
    }

    @Test
    void testCreate() {
        UserSnapshot user = this.mockUser();
        this.restTestClient.post().uri(LegalExpertProfileResource.LEGAL_EXPERT_PROFILES)
                .body(this.newProfile(user.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalExpertProfile.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getRequiresPrepayment()).isFalse();
                });
    }

    @Test
    void testCreateDuplicateTaxIdCode() {
        LegalExpertProfile profile = this.newProfile(this.mockUser().getId());
        profile.setTaxIdCode(PROFILE_0.getTaxIdCode());
        this.restTestClient.post().uri(LegalExpertProfileResource.LEGAL_EXPERT_PROFILES)
                .body(profile)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdate() {
        LegalExpertProfile created = this.createProfile();
        LegalExpertProfile update = this.newProfile(created.getUserSnapshot().getId());
        update.setSpecialtyArea("Updated area");
        this.restTestClient.put().uri(LegalExpertProfileResource.LEGAL_EXPERT_PROFILES + "/" + created.getId())
                .body(update)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalExpertProfile.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(created.getId());
                    assertThat(body.getSpecialtyArea()).isEqualTo("Updated area");
                });
    }

    @Test
    void testUpdateNotFound() {
        this.restTestClient.put().uri(LegalExpertProfileResource.LEGAL_EXPERT_PROFILES + "/" + UUID.randomUUID())
                .body(this.newProfile(UUID.randomUUID()))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdatePartial() {
        LegalExpertProfile created = this.createProfile();
        this.restTestClient.patch().uri(LegalExpertProfileResource.LEGAL_EXPERT_PROFILES)
                .body(List.of(LegalExpertProfile.builder().id(created.getId()).yearsOfExperience(25).build()))
                .exchange()
                .expectStatus().isOk();
        this.restTestClient.get().uri(LegalExpertProfileResource.LEGAL_EXPERT_PROFILES + "/" + created.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalExpertProfile.class)
                .value(body -> assertThat(body).isNotNull().extracting(LegalExpertProfile::getYearsOfExperience)
                        .isEqualTo(25));
    }

    @Test
    void testDelete() {
        LegalExpertProfile created = this.createProfile();
        this.restTestClient.delete().uri(LegalExpertProfileResource.LEGAL_EXPERT_PROFILES + "/" + created.getId())
                .exchange()
                .expectStatus().isOk();
        this.restTestClient.get().uri(LegalExpertProfileResource.LEGAL_EXPERT_PROFILES + "/" + created.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testFindSpecialtyReport() {
        when(this.userFinder.findByIds(anySet())).thenAnswer(invocation -> {
            Set<UUID> ids = invocation.getArgument(0);
            return ids.stream().map(id -> UserSnapshot.builder().id(id).firstName("Hydrated").build()).toList();
        });

        this.restTestClient.get().uri(LegalExpertProfileResource.LEGAL_EXPERT_PROFILES
                        + LegalExpertProfileResource.REPORT)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalExpertProfileSpecialtyReport[].class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body).extracting(LegalExpertProfileSpecialtyReport::getTotalSchedules)
                            .isSortedAccordingTo(Comparator.reverseOrder());
                    assertThat(body).filteredOn(item -> item.getSpecialtyArea().equals(PROFILE_0.getSpecialtyArea()))
                            .singleElement()
                            .satisfies(item -> {
                                assertThat(item.getTotalProfiles()).isGreaterThanOrEqualTo(2);
                                assertThat(item.getMostVeteranExpert().getId())
                                        .isEqualTo(PROFILE_3.getUserSnapshot().getId());
                                assertThat(item.getMostVeteranExpert().getFirstName()).isEqualTo("Hydrated");
                            });
                });
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

    private LegalExpertProfile createProfile() {
        UserSnapshot user = this.mockUser();
        return this.restTestClient.post().uri(LegalExpertProfileResource.LEGAL_EXPERT_PROFILES)
                .body(this.newProfile(user.getId()))
                .exchange().expectStatus().isOk()
                .expectBody(LegalExpertProfile.class).returnResult().getResponseBody();
    }
}
