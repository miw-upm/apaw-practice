package es.upm.miw.apaw_practice.domain.persistence_ports.university;

import es.upm.miw.apaw_practice.domain.models.university.Student;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface StudentPersistence {

    Student create(Student student);

    boolean existDni(String dni);

    Student readByDni(String dni);

    Stream<Student> findStudentsByClassroomSchool(String classroomSchool);
}