package es.upm.miw.apaw_practice.domain.persistence_ports.Class;

import es.upm.miw.apaw_practice.domain.models.Class.Professor;
import es.upm.miw.apaw_practice.domain.models.football.PrincipalReferee;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ProfessorPersistence {
    Stream<es.upm.miw.apaw_practice.domain.models.Class.Professor> readAll();

    Professor create(Professor professor);
}
