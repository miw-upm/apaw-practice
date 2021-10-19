package es.upm.miw.apaw_practice.domain.persistence_ports.Class;

import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface CoursePersistence {

    Stream<es.upm.miw.apaw_practice.domain.models.Class.Course> readAll();

}
