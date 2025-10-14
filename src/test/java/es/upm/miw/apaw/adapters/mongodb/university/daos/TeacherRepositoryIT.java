package es.upm.miw.apaw.adapters.mongodb.university.daos;

import es.upm.miw.apaw.adapters.mongodb.university.entities.TeacherEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class TeacherRepositoryIT {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void testFindByIdentificationCode() {
        String identificationCode = "T001";
        
        Optional<TeacherEntity> teacher = teacherRepository.findByIdentificationCode(identificationCode);
        
        assertThat(teacher).isPresent();
        assertThat(teacher.get().getIdentificationCode()).isEqualTo(identificationCode);
        assertThat(teacher.get().getFullName()).isEqualTo("TFN001");
        assertThat(teacher.get().getSpecialization()).isEqualTo("TS001");
        assertThat(teacher.get().getTenured()).isTrue();
    }

    @Test
    void testFindByIdentificationCodeNotFound() {
        String nonExistentCode = "T999NotFoundCode";
        
        Optional<TeacherEntity> teacher = teacherRepository.findByIdentificationCode(nonExistentCode);
        
        assertThat(teacher).isEmpty();
    }

    @Test
    void testFindByIdentificationCodeCaseSensitive() {
        String identificationCode = "t001";
        
        Optional<TeacherEntity> teacher = teacherRepository.findByIdentificationCode(identificationCode);
        
        assertThat(teacher).isEmpty();
    }

    @Test
    void testFindByIdentificationCodeWithSpaces() {
        String identificationCode = "T002";
        
        Optional<TeacherEntity> teacher = teacherRepository.findByIdentificationCode(identificationCode);
        
        assertThat(teacher).isPresent();
        assertThat(teacher.get().getIdentificationCode()).isEqualTo(identificationCode);
    }

    @Test
    void testFindByIdentificationCodeExactMatch() {
        String partialCode = "T00";
        
        Optional<TeacherEntity> teacher = teacherRepository.findByIdentificationCode(partialCode);
        
        assertThat(teacher).isEmpty();
    }

    @Test
    void testFindByIdentificationCodeEmptyString() {
        String emptyCode = "";
        
        Optional<TeacherEntity> teacher = teacherRepository.findByIdentificationCode(emptyCode);
        
        assertThat(teacher).isEmpty();
    }
}
