package es.upm.miw.apaw_practice.domain.persistence_ports.training;

import es.upm.miw.apaw_practice.domain.models.training.Lecturer;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerPersistence {
    Lecturer create(Lecturer lecturer);
}
