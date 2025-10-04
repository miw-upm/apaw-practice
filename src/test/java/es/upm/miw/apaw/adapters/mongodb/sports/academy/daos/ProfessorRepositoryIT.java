package es.upm.miw.apaw.adapters.mongodb.sports.academy.daos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class ProfessorRepositoryIT {

    @Autowired
    private ProfessorRepository professorRepository;

    @Test
    void testFindByUserDtoId(){
        assertTrue(this.professorRepository.findByUserDtoId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004")).isPresent());
        var professor = this.professorRepository.findByUserDtoId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004")).get();
        assertThat(professor).isNotNull();
        assertThat(professor.getUserDtoId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"));
        assertThat(professor.getLicenseNumber()).isEqualTo("ABC123");
        assertThat(professor.getSpecialization()).isEqualTo("Tennis");
    }
}
