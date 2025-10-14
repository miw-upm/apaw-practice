package es.upm.miw.apaw.domain.services.university;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.university.Teacher;
import es.upm.miw.apaw.domain.persistenceports.university.TeacherPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class TeacherServiceIT {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherPersistence teacherPersistence;

    @Test
    void testUpdate() {
        UUID teacherId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0104");
        
        Teacher updatedTeacher = Teacher.builder()
                .identificationCode("T005")
                .specialization("Updated Computer Science")
                .fullName("Updated Teacher Name")
                .tenured(false)
                .build();

        Teacher result = this.teacherService.update(teacherId, updatedTeacher);

        assertThat(result).isNotNull();
        assertThat(result.getIdentificationCode()).isEqualTo("T005");
        assertThat(result.getSpecialization()).isEqualTo("Updated Computer Science");
        assertThat(result.getFullName()).isEqualTo("Updated Teacher Name");
        assertThat(result.getTenured()).isFalse();
    }

    @Test
    void testUpdateWithNewIdentificationCode() {
        UUID teacherId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0105");
        
        Teacher updatedTeacher = Teacher.builder()
                .identificationCode("T9999")
                .specialization("New Specialization")
                .fullName("New Teacher Name")
                .tenured(true)
                .build();

        Teacher result = this.teacherService.update(teacherId, updatedTeacher);

        assertThat(result).isNotNull();
        assertThat(result.getIdentificationCode()).isEqualTo("T9999");
        assertThat(result.getSpecialization()).isEqualTo("New Specialization");
        assertThat(result.getFullName()).isEqualTo("New Teacher Name");
        assertThat(result.getTenured()).isTrue();
    }

    @Test
    void testUpdateWithConflictingIdentificationCode() {
        UUID teacherId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0104");
        
        // Try to update with an identification code that already exists (T002)
        Teacher updatedTeacher = Teacher.builder()
                .identificationCode("T002")
                .specialization("Updated Specialization")
                .fullName("Updated Teacher Name")
                .tenured(true)
                .build();

        assertThatThrownBy(() -> this.teacherService.update(teacherId, updatedTeacher))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("Identification code already exists: T002");
    }

    @Test
    void testUpdateNotFound() {
        UUID nonExistentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff9999");
        
        Teacher updatedTeacher = Teacher.builder()
                .identificationCode("T999")
                .specialization("New Specialization")
                .fullName("New Teacher Name")
                .tenured(true)
                .build();

        assertThatThrownBy(() -> this.teacherService.update(nonExistentId, updatedTeacher))
                .isInstanceOf(es.upm.miw.apaw.domain.exceptions.NotFoundException.class);
    }
}
