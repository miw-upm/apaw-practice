package es.upm.miw.apaw.domain.services.university;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.university.LessonDurationSearching;
import es.upm.miw.apaw.domain.models.university.Lesson;
import es.upm.miw.apaw.domain.models.university.Teacher;
import es.upm.miw.apaw.domain.persistenceports.university.SubjectAssignmentPersistence;
import es.upm.miw.apaw.domain.persistenceports.university.TeacherPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class TeacherService {
    private final TeacherPersistence teacherPersistence;
    private final SubjectAssignmentPersistence subjectAssignmentPersistence;

    @Autowired
    public TeacherService(TeacherPersistence teacherPersistence, SubjectAssignmentPersistence subjectAssignmentPersistence) {
        this.teacherPersistence = teacherPersistence;
        this.subjectAssignmentPersistence = subjectAssignmentPersistence;
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

    public LessonDurationSearching findLessonDurationSumByTeacherFullName(String fullName) {
        return new LessonDurationSearching(this.subjectAssignmentPersistence.findAll()
                .filter(subjectAssignment -> subjectAssignment.getTeachers().stream()
                        .anyMatch(teacher -> fullName.equalsIgnoreCase(teacher.getFullName())))
                .flatMap(subjectAssignment -> subjectAssignment.getLessons().stream())
                .distinct()
                .map(Lesson::getDuration)
                .filter(Objects::nonNull)
                .reduce(0, Integer::sum));
    }
}
