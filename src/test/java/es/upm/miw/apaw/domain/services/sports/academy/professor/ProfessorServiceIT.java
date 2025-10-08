package es.upm.miw.apaw.domain.services.sports.academy.professor;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.Professor;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import es.upm.miw.apaw.BaseSportsAcademyIT;
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
class ProfessorServiceIT extends BaseSportsAcademyIT {

    @Autowired
    private ProfessorService professorService;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testCreate() {
        UUID id = UUID.randomUUID();
        UserDto userDto = UserDto.builder()
                .id(id)
                .build();
        when(userRestClient.readById(id)).thenReturn(userDto);
        Professor professor = Professor.builder()
                .user(UserDto.builder().id(id).build())
                .licenseNumber("LIC123456")
                .specialization("CrossFit")
                .build();
        Professor professorCreated = this.professorService.create(professor);
        assertThat(professorCreated.getUser().getId()).isEqualTo(id);
        assertThat(professorCreated.getLicenseNumber()).isEqualTo("LIC123456");
        assertThat(professorCreated.getSpecialization()).isEqualTo("CrossFit");
    }
}
