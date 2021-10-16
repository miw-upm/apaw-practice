package es.upm.miw.apaw_practice.domain.persistence_ports.university;

import es.upm.miw.apaw_practice.domain.models.university.Classroom;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomPersistence {

    Classroom readBySchoolAndNumber(String school, Integer number);

    void delete(String school, Integer number);
}
