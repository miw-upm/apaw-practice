package es.upm.miw.apaw_practice.domain.persistence_ports.university;

import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface DegreePersistence {

    Stream<String> readAllTitles();
}
