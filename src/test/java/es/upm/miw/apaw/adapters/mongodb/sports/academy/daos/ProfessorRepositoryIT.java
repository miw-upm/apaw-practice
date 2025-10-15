package es.upm.miw.apaw.adapters.mongodb.sports.academy.daos;

import es.upm.miw.apaw.BaseSportsAcademyTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class ProfessorRepositoryIT extends BaseSportsAcademyTests {

    @Autowired
    private ProfessorRepository professorRepository;

    @Test
    void testFindByUserDtoId(){
        assertTrue(this.professorRepository.findByUserDtoId(professors[0].getUserDtoId()).isPresent());
        var professor = this.professorRepository.findByUserDtoId(professors[0].getUserDtoId()).get();
        assertThat(professor).isNotNull();
        assertThat(professor.getUserDtoId()).isEqualTo(professors[0].getUserDtoId());
        assertThat(professor.getLicenseNumber()).isEqualTo("ABC123");
        assertThat(professor.getSpecialization()).isEqualTo("Tennis");
    }
}
