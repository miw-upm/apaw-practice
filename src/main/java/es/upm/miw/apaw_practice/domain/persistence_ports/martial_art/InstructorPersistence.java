package es.upm.miw.apaw_practice.domain.persistence_ports.martial_art;

import es.upm.miw.apaw_practice.domain.models.martial_art.Instructor;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface InstructorPersistence {

    Stream<Instructor> readAll();

    Instructor create(Instructor instructor);

    Instructor update(String dni, Instructor instructor);

    Instructor read(String dni);

    boolean existsByDni(String dni);

    void delete(String dni);
}
