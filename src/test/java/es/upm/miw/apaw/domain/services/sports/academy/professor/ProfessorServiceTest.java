package es.upm.miw.apaw.domain.services.sports.academy.professor;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.Professor;
import es.upm.miw.apaw.domain.persistenceports.sports.academy.IProfessorPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import es.upm.miw.apaw.domain.services.sports.academy.ProfessorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class ProfessorServiceTest {
    @Autowired
    private ProfessorService professorService;
    @MockitoBean
    private IProfessorPersistence professorPersistence;
    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testCreate() {
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002");
        UserDto userDto = UserDto.builder()
                .id(id)
                .build();
        Professor professor = Professor.builder()
                .user(UserDto.builder().id(id).build())
                .specialization("Tennis")
                .licenseNumber("LIC123456")
                .build();
        when(userRestClient.readById(id)).thenReturn(userDto);
        when(professorPersistence.create(professor)).thenReturn(professor);

        Professor result = professorService.create(professor);

        assertThat(result.getUser().getId()).isEqualTo(id);
        assertThat(result.getSpecialization()).isEqualTo("Tennis");
        assertThat(result.getLicenseNumber()).isEqualTo("LIC123456");
    }
}
