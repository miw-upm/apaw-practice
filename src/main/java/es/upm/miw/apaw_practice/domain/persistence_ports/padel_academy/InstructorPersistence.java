package es.upm.miw.apaw_practice.domain.persistence_ports.padel_academy;

import es.upm.miw.apaw_practice.domain.models.padel_academy.Instructor;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorPersistence {
    Instructor read(String dni);
    Instructor update(Instructor instructor);

    Instructor readByName(String name);
}
