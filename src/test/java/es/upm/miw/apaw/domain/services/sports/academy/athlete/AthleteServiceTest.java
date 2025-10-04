package es.upm.miw.apaw.domain.services.sports.academy.athlete;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.Athlete;
import es.upm.miw.apaw.domain.models.sports.academy.enums.Gender;
import es.upm.miw.apaw.domain.persistenceports.sports.academy.IAthletePersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import es.upm.miw.apaw.domain.services.sports.academy.AthleteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class AthleteServiceTest {
    @Autowired
    private AthleteService athleteService;
    @MockitoBean
    private IAthletePersistence athletePersistence;
    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testGetById() {
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002");
        Athlete athlete = Athlete.builder()
                .user(UserDto.builder().id(id).build())
                .gender(Gender.MALE)
                .height(1.78)
                .weight(72)
                .birthDate(LocalDate.of(2000, 6, 20))
                .legalGuardians(new ArrayList<>())
                .sportModalities(new ArrayList<>())
                .build();
        UserDto userDto = UserDto.builder()
                .id(id)
                .firstName("Mario Rossi")
                .mobile("+34711036811")
                .build();

        when(athletePersistence.getById(id)).thenReturn(athlete);
        when(userRestClient.readById(id)).thenReturn(userDto);

        Athlete result = athleteService.getById(id);

        assertThat(result.getUser().getId()).isEqualTo(id);
        assertThat(result.getUser().getFirstName()).isEqualTo("Mario Rossi");
        assertThat(result.getUser().getMobile()).isEqualTo("+34711036811");
        assertThat(result.getGender()).isEqualTo(Gender.MALE);
        assertThat(result.getHeight()).isEqualTo(1.78);
        assertThat(result.getWeight()).isEqualTo(72);
        assertThat(result.getBirthDate()).isEqualTo(LocalDate.of(2000, 6, 20));
        assertThat(result.getLegalGuardians()).isEmpty();
        assertThat(result.getSportModalities()).isEmpty();
    }
}
