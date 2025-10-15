package es.upm.miw.apaw.adapters.mongodb.university.persistence;

import es.upm.miw.apaw.adapters.mongodb.university.daos.TeacherRepository;
import es.upm.miw.apaw.adapters.mongodb.university.entities.TeacherEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.university.Teacher;
import es.upm.miw.apaw.domain.persistenceports.university.TeacherPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class TeacherPersistenceMongodbIT {

    @Autowired
    private TeacherPersistence teacherPersistence;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void testGetById() {
        UUID teacherId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0101");
        
        Teacher teacher = teacherPersistence.getById(teacherId);
        
        assertThat(teacher).isNotNull();
        assertThat(teacher.getIdentificationCode()).isEqualTo("T002");
        assertThat(teacher.getFullName()).isEqualTo("TFN002");
        assertThat(teacher.getSpecialization()).isEqualTo("TS002");
        assertThat(teacher.getTenured()).isTrue();
    }

    @Test
    void testGetByIdNotFound() {
        UUID nonExistentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff9999");
        
        assertThatThrownBy(() -> teacherPersistence.getById(nonExistentId))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Teacher not found with id: " + nonExistentId);
    }

    @Test
    void testUpdate() {
        UUID teacherId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0107");
        
        Teacher updatedTeacher = Teacher.builder()
                .identificationCode("T008")
                .fullName("Dr. Smith Updated")
                .specialization("Advanced Mathematics")
                .tenured(false)
                .build();

        Teacher result = teacherPersistence.update(teacherId, updatedTeacher);
        
        assertThat(result).isNotNull();
        assertThat(result.getIdentificationCode()).isEqualTo("T008");
        assertThat(result.getFullName()).isEqualTo("Dr. Smith Updated");
        assertThat(result.getSpecialization()).isEqualTo("Advanced Mathematics");
        assertThat(result.getTenured()).isFalse();

        Optional<TeacherEntity> teacherEntity = teacherRepository.findById(teacherId);
        assertThat(teacherEntity).isPresent();
        assertThat(teacherEntity.get().getFullName()).isEqualTo("Dr. Smith Updated");
    }

    @Test
    void testExistIdentificationCodeTrue() {
        boolean exists = teacherPersistence.existIdentificationCode("T001");
        assertThat(exists).isTrue();
    }

    @Test
    void testExistIdentificationCodeFalse() {
        boolean exists = teacherPersistence.existIdentificationCode("T010");
        assertThat(exists).isFalse();
    }

    @Test
    void testUpdateWithNewTeacher() {
        UUID newTeacherId = UUID.randomUUID();
        
        Teacher newTeacher = Teacher.builder()
                .identificationCode("T011")
                .fullName("New Teacher")
                .specialization("Computer Science")
                .tenured(true)
                .build();

        Teacher result = teacherPersistence.update(newTeacherId, newTeacher);
        
        assertThat(result).isNotNull();
        assertThat(result.getIdentificationCode()).isEqualTo("T011");
        assertThat(result.getFullName()).isEqualTo("New Teacher");
        assertThat(result.getSpecialization()).isEqualTo("Computer Science");
        assertThat(result.getTenured()).isTrue();

        Optional<TeacherEntity> teacherEntity = teacherRepository.findById(newTeacherId);
        assertThat(teacherEntity).isPresent();
        assertThat(teacherEntity.get().getFullName()).isEqualTo("New Teacher");
    }
}
