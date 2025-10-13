package es.upm.miw.apaw.domain.services.university;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.university.Teacher;
import es.upm.miw.apaw.domain.persistenceports.university.TeacherPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TeacherService {
    private final TeacherPersistence teacherPersistence;

    @Autowired
    public TeacherService(TeacherPersistence teacherPersistence) {
        this.teacherPersistence = teacherPersistence;
    }

    public Teacher update(UUID id, Teacher teacher) {
        var existingTeacher = this.teacherPersistence.getById(id);
        
        if (!existingTeacher.getIdentificationCode().equals(teacher.getIdentificationCode())) {
            this.assertIdentificationCodeNotExists(teacher.getIdentificationCode());
        }
        
        existingTeacher.setIdentificationCode(teacher.getIdentificationCode());
        existingTeacher.setSpecialization(teacher.getSpecialization());
        existingTeacher.setFullName(teacher.getFullName());
        existingTeacher.setTenured(teacher.getTenured());
        
        return this.teacherPersistence.update(id, existingTeacher);
    }

    private void assertIdentificationCodeNotExists(String identificationCode) {
        if (this.teacherPersistence.existIdentificationCode(identificationCode)) {
            throw new ConflictException("Identification code already exists: " + identificationCode);
        }
    }
}
