package es.upm.miw.apaw_practice.domain.services.university;

import es.upm.miw.apaw_practice.domain.models.university.Degree;
import es.upm.miw.apaw_practice.domain.models.university.Student;
import es.upm.miw.apaw_practice.domain.models.university.University;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.StudentPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.TeacherPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {

    private final StudentPersistence studentPersistence;

    private final TeacherPersistence teacherPersistence;

    @Autowired
    public StudentService(StudentPersistence studentPersistence, TeacherPersistence teacherPersistence) {
        this.studentPersistence = studentPersistence;
        this.teacherPersistence = teacherPersistence;
    }

    public void update(String email, Student student) {
        studentPersistence.update(email, student);
    }

    public boolean existsEmail(String email) {
        return studentPersistence.existEmail(email);
    }

    public Stream<String> findDistinctStudentEmailsByTeacherLastName(String lastName) {
        Set<Degree> degrees = teacherPersistence
                .readAll()
                .filter(teacher -> teacher
                        .getLastName()
                        .equals(lastName))
                .flatMap(teacher -> {
                    University workplace = teacher.getWorkplace();
                    if (workplace != null) {
                        return workplace.getDegreesOffered().stream();
                    } else {
                        return Stream.empty();
                    }
                })
                .collect(Collectors.toSet());
        return studentPersistence
                .readAll()
                .filter(student -> student
                        .getEnrolledDegrees()
                        .stream()
                        .anyMatch(degrees::contains)
                ).map(Student::getEmail).distinct();
    }
}
