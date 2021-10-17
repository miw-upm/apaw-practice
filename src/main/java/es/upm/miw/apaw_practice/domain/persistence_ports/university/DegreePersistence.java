package es.upm.miw.apaw_practice.domain.persistence_ports.university;

import es.upm.miw.apaw_practice.domain.models.university.Degree;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface DegreePersistence {

    Stream<Degree> readAll();
}
