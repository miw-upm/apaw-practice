package es.upm.miw.apaw.domain.services.sports.academy.professor;

import es.upm.miw.apaw.adapters.mongodb.sports.academy.daos.ProfessorRepository;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.Professor;
import es.upm.miw.apaw.domain.models.sports.academy.dtos.CreateProfessor;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import es.upm.miw.apaw.BaseSportsAcademyTests;
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
class ProfessorServiceIT extends BaseSportsAcademyTests {

    @Autowired
    private ProfessorService professorService;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testCreate(@Autowired ProfessorRepository professorRepository) {
        UUID id = UUID.randomUUID();
        UserDto userDto = UserDto.builder()
                .id(id)
                .build();
        when(userRestClient.readById(id)).thenReturn(userDto);
        CreateProfessor professor = CreateProfessor.builder()
                .userId(id)
                .licenseNumber("LIC123456")
                .specialization("CrossFit")
                .build();
        Professor professorCreated = this.professorService.create(professor);
        assertThat(professorCreated.getUser().getId()).isEqualTo(id);
        assertThat(professorCreated.getLicenseNumber()).isEqualTo("LIC123456");
        assertThat(professorCreated.getSpecialization()).isEqualTo("CrossFit");
        professorRepository.deleteById(id);
    }
}
