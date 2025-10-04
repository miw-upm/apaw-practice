package es.upm.miw.apaw.functionaltests.sports.academy;

import es.upm.miw.apaw.adapters.resources.sports.academy.AthleteResource;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.Athlete;
import es.upm.miw.apaw.domain.models.sports.academy.enums.Gender;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AthleteResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testGetAthleteById() {
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002");
        UserDto userDto = UserDto.builder()
                .id(id)
                .firstName("Mario Rossi")
                .mobile("+34711036811")
                .build();
        when(userRestClient.readById(id)).thenReturn(userDto);
        webTestClient.get()
                .uri(AthleteResource.ATHLETES + AthleteResource.ID_ID, id.toString())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Athlete.class)
                .value(athlete -> {
                    assertThat(athlete).isNotNull();
                    assertThat(athlete.getUser().getId()).isEqualTo(id);
                    assertThat(athlete.getUser().getFirstName()).isEqualTo("Mario Rossi");
                    assertThat(athlete.getUser().getMobile()).isEqualTo("+34711036811");
                    assertThat(athlete.getGender()).isEqualTo(Gender.MALE);
                    assertThat(athlete.getBirthDate()).isEqualTo(LocalDate.of(2000, 6, 20));
                    assertThat(athlete.getHeight()).isEqualTo(1.78);
                    assertThat(athlete.getWeight()).isEqualTo(72.0);
                    assertThat(athlete.getSportModalities()).isEmpty();
                    assertThat(athlete.getLegalGuardians()).hasSize(1);
                });
      }
}