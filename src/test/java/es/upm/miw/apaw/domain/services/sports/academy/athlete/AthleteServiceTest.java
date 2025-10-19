package es.upm.miw.apaw.domain.services.sports.academy.athlete;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.Athlete;
import es.upm.miw.apaw.domain.models.sports.academy.LegalGuardian;
import es.upm.miw.apaw.domain.models.sports.academy.Professor;
import es.upm.miw.apaw.domain.models.sports.academy.SportModality;
import es.upm.miw.apaw.domain.models.sports.academy.enums.Gender;
import es.upm.miw.apaw.domain.models.sports.academy.enums.Level;
import es.upm.miw.apaw.domain.models.sports.academy.enums.RelationShip;
import es.upm.miw.apaw.domain.persistenceports.sports.academy.IAthletePersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import es.upm.miw.apaw.domain.services.sports.academy.AthleteService;
import es.upm.miw.apaw.domain.services.sports.academy.LegalGuardianService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Stream;

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
    @MockitoBean
    private LegalGuardianService legalGuardianService;

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

    @Test
    void testGetUniqueProfessorSpecializationsByLegalGuardian() {
        String secondMobile = "34711036812";
        UUID legalGuardianId = UUID.fromString("bbbbbbbb-bbbb-cccc-dddd-eeeeffff0003");
        UUID athleteId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002");
        UUID sportModalityId1 = UUID.fromString("11111111-bbbb-cccc-dddd-eeeeffff0001");
        UUID sportModalityId2 = UUID.fromString("22222222-bbbb-cccc-dddd-eeeeffff0002");
        UUID professorId1 = UUID.fromString("33333333-bbbb-cccc-dddd-eeeeffff0003");
        UUID professorId2 = UUID.fromString("44444444-bbbb-cccc-dddd-eeeeffff0004");

        Athlete athlete1 = Athlete.builder()
                .user(UserDto.builder().id(athleteId).build())
                .legalGuardians(new ArrayList<>())
                .sportModalities(new ArrayList<>())
                .build();

        var legalGuardian = LegalGuardian.builder()
                .user(UserDto.builder()
                        .id(legalGuardianId)
                        .firstName("Luigi Rossi")
                        .mobile("+34711036812")
                        .build())
                .secondMobile(secondMobile)
                .relationShip(RelationShip.AUNT)
                .build();
        athlete1.getLegalGuardians().add(
                legalGuardian
        );

        var sportModality1 = SportModality.builder()
                .id(sportModalityId1)
                .title("Tennis")
                .professor(Professor.builder()
                        .user(UserDto.builder()
                                .id(professorId1)
                                .firstName("Anna Verdi")
                                .mobile("+34711036813")
                                .build())
                        .specialization("Fitness")
                        .build())
                .build();

        var sportModality2 = SportModality.builder()
                .id(sportModalityId2)
                .title("Swimming")
                .professor(Professor.builder()
                        .user(UserDto.builder()
                                .id(professorId2)
                                .firstName("Laura Neri")
                                .mobile("+34711036814")
                                .build())
                        .specialization("Tennis")
                        .build())
                .build();

        athlete1.getSportModalities().add(sportModality1);
        athlete1.getSportModalities().add(sportModality2);

        var returnedLegalGuardians = Stream.of(legalGuardian);
        when(legalGuardianService.getBySecondMobile(secondMobile)).thenReturn(returnedLegalGuardians);
        when(athletePersistence.getByLegalGuardians(returnedLegalGuardians)).thenReturn(Stream.of(athlete1));

        var result = athleteService.getUniqueProfessorSpecializations(secondMobile);

        assertThat(result).containsExactlyInAnyOrder("Fitness", "Tennis");
    }

    @Test
    void testGetAverageHeightByLegalGuardian() {
        var relationShip = RelationShip.SIBLING;
        UUID legalGuardianId = UUID.fromString("bbbbbbbb-bbbb-cccc-dddd-eeeeffff0003");
        UUID athleteId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002");
        UUID sportModalityId1 = UUID.fromString("11111111-bbbb-cccc-dddd-eeeeffff0001");
        UUID sportModalityId2 = UUID.fromString("22222222-bbbb-cccc-dddd-eeeeffff0002");
        UUID professorId1 = UUID.fromString("33333333-bbbb-cccc-dddd-eeeeffff0003");
        UUID professorId2 = UUID.fromString("44444444-bbbb-cccc-dddd-eeeeffff0004");

        Athlete athlete1 = Athlete.builder()
                .user(UserDto.builder().id(athleteId).build())
                .legalGuardians(new ArrayList<>())
                .sportModalities(new ArrayList<>())
                .height(1.75)
                .build();

        var legalGuardian = LegalGuardian.builder()
                .user(UserDto.builder()
                        .id(legalGuardianId)
                        .firstName("Luigi Rossi")
                        .mobile("+34711036812")
                        .build())
                .secondMobile("+5549988706208")
                .relationShip(relationShip)
                .build();
        athlete1.getLegalGuardians().add(
                legalGuardian
        );

        var sportModality1 = SportModality.builder()
                .id(sportModalityId1)
                .title("Tennis")
                .level(Level.ADVANCED)
                .professor(Professor.builder()
                        .user(UserDto.builder()
                                .id(professorId1)
                                .firstName("Anna Verdi")
                                .mobile("+34711036813")
                                .build())
                        .specialization("Fitness")
                        .build())
                .build();

        var sportModality2 = SportModality.builder()
                .id(sportModalityId2)
                .title("Swimming")
                .level(Level.ADVANCED)
                .professor(Professor.builder()
                        .user(UserDto.builder()
                                .id(professorId2)
                                .firstName("Laura Neri")
                                .mobile("+34711036814")
                                .build())
                        .specialization("Tennis")
                        .build())
                .build();

        athlete1.getSportModalities().add(sportModality1);
        athlete1.getSportModalities().add(sportModality2);

        var returnedLegalGuardians = Stream.of(legalGuardian);
        when(legalGuardianService.getByRelationShip(relationShip)).thenReturn(returnedLegalGuardians);
        when(athletePersistence.getByLegalGuardians(returnedLegalGuardians)).thenReturn(Stream.of(athlete1));

        var result = athleteService.getAverageHeightByLegalGuardian(relationShip);

        assertThat(result).isEqualTo(athlete1.getHeight());
    }
}
