package es.upm.miw.apaw_practice.domain.persistence_ports.university;

import es.upm.miw.apaw_practice.domain.models.university.Teacher;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface TeacherPersistence {

    Stream<Teacher> readAll();

    Teacher create(Teacher teacher);

    Teacher update(String nationalId, Teacher teacher);

    Teacher read(String nationalId);

    void delete(String nationalId);

    boolean existNationalId(String nationalId);

}
