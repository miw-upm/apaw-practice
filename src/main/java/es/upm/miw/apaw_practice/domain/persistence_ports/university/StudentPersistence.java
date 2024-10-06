package es.upm.miw.apaw_practice.domain.persistence_ports.university;

import es.upm.miw.apaw_practice.domain.models.university.Student;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface StudentPersistence {

    Stream<Student> readAll();

    Student create(Student student);

    Student update(String email, Student student);

    Student read(String email);

    boolean existEmail(String email);

}
