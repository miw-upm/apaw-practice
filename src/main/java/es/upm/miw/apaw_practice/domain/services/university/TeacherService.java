package es.upm.miw.apaw_practice.domain.services.university;

import es.upm.miw.apaw_practice.domain.models.university.Degree;
import es.upm.miw.apaw_practice.domain.models.university.Student;
import es.upm.miw.apaw_practice.domain.models.university.Teacher;
import es.upm.miw.apaw_practice.domain.models.university.University;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.StudentPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.TeacherPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TeacherService {

    private final TeacherPersistence teacherPersistence;

    private final StudentPersistence studentPersistence;

    @Autowired
    public TeacherService(TeacherPersistence teacherPersistence, StudentPersistence studentPersistence) {
        this.teacherPersistence = teacherPersistence;
        this.studentPersistence = studentPersistence;
    }

    public void delete(String nationalId) {
        teacherPersistence.delete(nationalId);
    }

    public Stream<String> findDistinctTeacherNationalIdFromStudentFirstName(String studentFirstName) {
        Set<Degree> degrees = studentPersistence
                .readAll()
                .filter(student -> student.getFirstName().equals(studentFirstName))
                .map(Student::getEnrolledDegrees)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        return teacherPersistence
                .readAll()
                .filter(teacher -> {
                    University workPlace = teacher.getWorkplace();
                    return workPlace != null && workPlace
                            .getDegreesOffered()
                            .stream()
                            .anyMatch(degrees::contains);
                }).map(Teacher::getNationalId)
                .distinct();
    }

}
