package es.upm.miw.apaw_practice.domain.persistence_ports.university;

import es.upm.miw.apaw_practice.domain.models.university.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPersistence {

    Student create(Student student);

    boolean existDni(String dni);

    Student readByDni(String dni);
}
