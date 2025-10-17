package es.upm.miw.apaw.functionaltests.sports.academy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.upm.miw.apaw.BaseSportsAcademyTests;
import es.upm.miw.apaw.adapters.resources.sports.academy.AthleteResource;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.Athlete;
import es.upm.miw.apaw.domain.models.sports.academy.dtos.SportModalitiesLevelsPercentage;
import es.upm.miw.apaw.domain.models.sports.academy.enums.Gender;
import es.upm.miw.apaw.domain.models.sports.academy.enums.Level;
import es.upm.miw.apaw.domain.models.sports.academy.enums.RelationShip;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class AthleteResourceFT extends BaseSportsAcademyTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserRestClient userRestClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetAthleteById() {
        UUID id = athletes[0].getUserDtoId();
        UserDto userDto = UserDto.builder()
                .id(id)
                .firstName("Mario Rossi")
                .mobile("+34711036811")
                .build();
        when(userRestClient.readById(id)).thenReturn(userDto);
        UserDto legalGuardian = UserDto.builder()
                .id(athletes[0].getLegalGuardians().getFirst().getUserDtoId())
                .firstName("Luigi Rossi")
                .mobile("+34711036812")
                .build();
        when(userRestClient.readById(legalGuardian.getId())).thenReturn(legalGuardian);
        UserDto professor1 = UserDto.builder()
                .id(athletes[0].getSportModalities().getFirst().getProfessor().getUserDtoId())
                .firstName("Anna Verdi")
                .mobile("+34711036813")
                .build();
        when(userRestClient.readById(professor1.getId())).thenReturn(professor1);
        UserDto professor2 = UserDto.builder()
                .id(athletes[0].getSportModalities().getLast().getProfessor().getUserDtoId())
                .firstName("Laura Neri")
                .mobile("+34711036814")
                .build();
        when(userRestClient.readById(professor2.getId())).thenReturn(professor2);
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
                    assertThat(athlete.getLegalGuardians()).hasSize(1);
                    assertThat(athlete.getLegalGuardians().getFirst().getUser().getId()).isEqualTo(athletes[0].getLegalGuardians().getFirst().getUserDtoId());
                    assertThat(athlete.getLegalGuardians().getFirst().getUser().getFirstName()).isEqualTo("Luigi Rossi");
                    assertThat(athlete.getLegalGuardians().getFirst().getUser().getMobile()).isEqualTo("+34711036812");
                    assertThat(athlete.getLegalGuardians().getFirst().getSecondMobile()).isEqualTo("34711036822");
                    assertThat(athlete.getLegalGuardians().getFirst().getRelationShip()).isEqualTo(RelationShip.AUNT);
                    assertThat(athlete.getSportModalities()).hasSize(2);
                    assertThat(athlete.getSportModalities().getFirst().getProfessor().getUser().getId()).isEqualTo(athletes[0].getSportModalities().getFirst().getProfessor().getUserDtoId());
                    assertThat(athlete.getSportModalities().getFirst().getProfessor().getUser().getFirstName()).isEqualTo("Anna Verdi");
                    assertThat(athlete.getSportModalities().getFirst().getProfessor().getUser().getMobile()).isEqualTo("+34711036813");
                    assertThat(athlete.getSportModalities().getLast().getProfessor().getUser().getId()).isEqualTo(athletes[0].getSportModalities().getLast().getProfessor().getUserDtoId());
                    assertThat(athlete.getSportModalities().getLast().getProfessor().getUser().getFirstName()).isEqualTo("Laura Neri");
                    assertThat(athlete.getSportModalities().getLast().getProfessor().getUser().getMobile()).isEqualTo("+34711036814");
                });
    }

    @Test
    void testGetUniqueProfessorSpecializationsByLegalGuardian() throws Exception {
        String responseBody = webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(AthleteResource.ATHLETES + AthleteResource.SPORT_MODALITY_PROFESSOR_SPECIALIZATIONS)
                        .queryParam("secondMobile", athletes[0].getLegalGuardians().getFirst().getSecondMobile())
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        List<String> result = objectMapper.readValue(responseBody, new TypeReference<>() {});

        assertThat(result)
                .isNotEmpty()
                .contains("Tennis", "Swimming")
                .doesNotHaveDuplicates();
    }

    @Test
    void testGetPercentageOfSportModalityLevelsByLegalGuardian() throws Exception {
        String relationShip = RelationShip.FATHER.name();
        String responseBody = webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(AthleteResource.ATHLETES + AthleteResource.SPORT_MODALITY_LEVELS)
                        .queryParam("relationShip", relationShip)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        List<SportModalitiesLevelsPercentage> result = objectMapper.readValue(responseBody, new TypeReference<>() {});

        assertThat(result)
                .isNotEmpty()
                .contains(new SportModalitiesLevelsPercentage(Level.BEGINNER, 50.0),
                        new SportModalitiesLevelsPercentage(Level.INTERMEDIATE, 50.0))
                .doesNotHaveDuplicates();
    }
}