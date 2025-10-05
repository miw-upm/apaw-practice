package es.upm.miw.apaw.domain.services.sports.academy.athlete;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.Athlete;
import es.upm.miw.apaw.domain.models.sports.academy.enums.Gender;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import es.upm.miw.apaw.domain.services.sports.academy.AthleteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class AthleteServiceIT {

    @Autowired
    private AthleteService athleteService;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testGetById() {
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002");
        UserDto userDto = UserDto.builder()
                .id(id)
                .firstName("Mario Rossi")
                .mobile("+34711036811")
                .build();
        when(userRestClient.readById(id)).thenReturn(userDto);
        Athlete athlete = this.athleteService.getById(id);
        assertThat(athlete.getUser().getId()).isEqualTo(id);
        assertThat(athlete.getUser().getFirstName()).isEqualTo("Mario Rossi");
        assertThat(athlete.getUser().getMobile()).isEqualTo("+34711036811");
        assertThat(athlete.getGender()).isEqualTo(Gender.MALE);
        assertThat(athlete.getBirthDate()).isEqualTo(LocalDate.of(2000, 6, 20));
        assertThat(athlete.getHeight()).isEqualTo(1.78);
        assertThat(athlete.getWeight()).isEqualTo(72.0);
        assertThat(athlete.getSportModalities()).hasSize(2);
        assertThat(athlete.getLegalGuardians()).hasSize(1);
    }
}
