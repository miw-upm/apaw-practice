package es.upm.miw.apaw_practice.domain.persistence_ports.university;

import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomPersistence {

    void delete(String school, Integer number);
}
