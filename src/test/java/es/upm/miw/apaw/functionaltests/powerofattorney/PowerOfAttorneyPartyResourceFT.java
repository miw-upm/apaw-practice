package es.upm.miw.apaw.functionaltests.powerofattorney;

import es.upm.miw.apaw.adapters.in.powerofattorney.PowerOfAttorneyPartyResource;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.powerofattorney.CreationPowerOfAttorneyParty;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyParty;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyPartyPatch;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.PowerOfAttorneyPartySeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PowerOfAttorneyPartyResourceFT {

    @LocalServerPort
    private int port;

    private RestTestClient restTestClient;

    @MockitoBean
    private UserFinder userFinder;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port)
                .build();

        when(this.userFinder.read(any(UUID.class)))
                .thenAnswer(invocation -> this.findUser(invocation.getArgument(0)));

        when(this.userFinder.findByIds(any()))
                .thenAnswer(invocation -> {
                    Set<UUID> ids = invocation.getArgument(0);
                    return List.of(PARTY_0, PARTY_1, PARTY_2, PARTY_3, PARTY_4, PARTY_5)
                            .stream()
                            .map(PowerOfAttorneyParty::getUserSnapshot)
                            .filter(user -> ids.contains(user.getId()))
                            .distinct()
                            .toList();
                });
    }

    private UserSnapshot findUser(UUID id) {
        return List.of(PARTY_0, PARTY_1, PARTY_2, PARTY_3, PARTY_4, PARTY_5)
                .stream()
                .map(PowerOfAttorneyParty::getUserSnapshot)
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Test
    void testCreate() {
        UUID userId = PARTY_0.getUserSnapshot().getId();

        this.restTestClient.post()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES)
                .body(new CreationPowerOfAttorneyParty(
                        40,
                        true,
                        null,
                        false,
                        userId))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(PowerOfAttorneyParty.class)
                .value(body -> {
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getAge()).isEqualTo(40);
                    assertThat(body.getFullMentalCapacity()).isTrue();
                    assertThat(body.getRepresentationCompany()).isFalse();
                    assertThat(body.getUserSnapshot().getId()).isEqualTo(userId);
                });
    }

    @Test
    void testCreateInvalid() {
        this.restTestClient.post()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES)
                .body(new CreationPowerOfAttorneyParty(
                        null,
                        true,
                        null,
                        false,
                        PARTY_0.getUserSnapshot().getId()))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testRead() {
        this.restTestClient.get()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES + "/" + ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PowerOfAttorneyParty.class)
                .value(body -> {
                    assertThat(body.getId()).isEqualTo(ID_0);
                    assertThat(body.getAge()).isEqualTo(PARTY_0.getAge());
                    assertThat(body.getFullMentalCapacity())
                            .isEqualTo(PARTY_0.getFullMentalCapacity());
                    assertThat(body.getUserSnapshot().getId())
                            .isEqualTo(PARTY_0.getUserSnapshot().getId());
                });
    }

    @Test
    void testReadNotFound() {
        this.restTestClient.get()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES + "/" + UUID.randomUUID())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testFindAll() {
        this.restTestClient.get()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PowerOfAttorneyParty[].class)
                .value(body -> assertThat(body)
                        .extracting(PowerOfAttorneyParty::getId)
                        .contains(ID_0, ID_1, ID_2, ID_3, ID_4, ID_5));
    }

    @Test
    void testUpdate() {
        PowerOfAttorneyParty party = this.readParty(ID_0);

        this.restTestClient.put()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES + "/" + ID_0)
                .body(new CreationPowerOfAttorneyParty(
                        36,
                        false,
                        "Updated Company",
                        true,
                        party.getUserSnapshot().getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody(PowerOfAttorneyParty.class)
                .value(body -> {
                    assertThat(body.getId()).isEqualTo(ID_0);
                    assertThat(body.getAge()).isEqualTo(36);
                    assertThat(body.getFullMentalCapacity()).isFalse();
                    assertThat(body.getCompanyName()).isEqualTo("Updated Company");
                    assertThat(body.getRepresentationCompany()).isTrue();
                });
    }

    @Test
    void testUpdateNotFound() {
        this.restTestClient.put()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES + "/" + UUID.randomUUID())
                .body(new CreationPowerOfAttorneyParty(
                        36,
                        true,
                        null,
                        false,
                        PARTY_0.getUserSnapshot().getId()))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDelete() {
        PowerOfAttorneyParty party = this.createParty();

        this.restTestClient.delete()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES + "/" + party.getId())
                .exchange()
                .expectStatus().isNoContent();

        this.restTestClient.get()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES + "/" + party.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchAge() {

        PowerOfAttorneyParty party = this.createParty();

        this.restTestClient.patch()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES)
                .body(List.of(new PowerOfAttorneyPartyPatch(party.getId(), 36, null)))
                .exchange()
                .expectStatus().isOk();

        this.restTestClient.get()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES + "/" + party.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(PowerOfAttorneyParty.class)
                .value(body -> {
                    assertThat(body.getAge()).isEqualTo(36);
                    assertThat(body.getFullMentalCapacity()).isTrue();
                });
    }

    @Test
    void testPatchFullMentalCapacity() {

        PowerOfAttorneyParty party = this.createParty();

        this.restTestClient.patch()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES)
                .body(List.of(new PowerOfAttorneyPartyPatch(party.getId(), null, true)))
                .exchange()
                .expectStatus().isOk();

        this.restTestClient.get()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES + "/" + party.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(PowerOfAttorneyParty.class)
                .value(body -> {
                    assertThat(body.getAge()).isEqualTo(party.getAge());
                    assertThat(body.getFullMentalCapacity()).isTrue();
                });
    }

    @Test
    void testPatchBothFields() {
        this.restTestClient.patch()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES)
                .body(List.of(new PowerOfAttorneyPartyPatch(ID_3, 42, false)))
                .exchange()
                .expectStatus().isOk();

        this.restTestClient.get()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES + "/" + ID_3)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PowerOfAttorneyParty.class)
                .value(body -> {
                    assertThat(body.getAge()).isEqualTo(42);
                    assertThat(body.getFullMentalCapacity()).isFalse();
                });
    }

    @Test
    void testPatchWithoutValues() {
        this.restTestClient.patch()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES)
                .body(List.of(new PowerOfAttorneyPartyPatch(ID_4, null, null)))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatchNotFound() {
        this.restTestClient.patch()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES)
                .body(List.of(new PowerOfAttorneyPartyPatch(UUID.randomUUID(), 30, true)))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchRepeatedIds() {
        this.restTestClient.patch()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES)
                .body(List.of(
                        new PowerOfAttorneyPartyPatch(ID_0, 30, null),
                        new PowerOfAttorneyPartyPatch(ID_0, null, false)))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatchEmpty() {
        this.restTestClient.patch()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES)
                .body(List.of())
                .exchange()
                .expectStatus().isBadRequest();
    }

    private PowerOfAttorneyParty createParty() {
        UUID userId = PARTY_0.getUserSnapshot().getId();

        return this.restTestClient.post()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES)
                .body(new CreationPowerOfAttorneyParty(
                        30,
                        true,
                        null,
                        false,
                        userId))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(PowerOfAttorneyParty.class)
                .returnResult()
                .getResponseBody();
    }

    private PowerOfAttorneyParty readParty(UUID id) {
        return this.restTestClient.get()
                .uri(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES + "/" + id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PowerOfAttorneyParty.class)
                .returnResult()
                .getResponseBody();
    }
}