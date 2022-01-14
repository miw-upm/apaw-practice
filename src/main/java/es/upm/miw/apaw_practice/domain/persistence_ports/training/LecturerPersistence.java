package es.upm.miw.apaw_practice.domain.persistence_ports.training;

import es.upm.miw.apaw_practice.domain.models.training.Lecturer;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface LecturerPersistence {
    Lecturer readByDni(String dni);

    Stream<Lecturer> readAll();

    Lecturer update(Lecturer lecturer);
}
