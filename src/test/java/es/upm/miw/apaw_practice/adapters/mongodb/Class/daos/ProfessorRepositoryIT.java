package es.upm.miw.apaw_practice.adapters.mongodb.Class.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.ProfessorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class ProfessorRepositoryIT {
    @Autowired
    private ProfessorRepository professorRepository;

    @Test
    void testCreateAndRead(){
        List<ProfessorEntity> list = this.professorRepository.findAll();
        assertTrue(list.stream()
                .anyMatch(myProfessor -> "espanol".equals(myProfessor.getCourse())));
    }
}
