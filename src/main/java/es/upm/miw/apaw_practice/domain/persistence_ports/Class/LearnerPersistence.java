package es.upm.miw.apaw_practice.domain.persistence_ports.Class;

import es.upm.miw.apaw_practice.domain.models.Class.Learner;
import es.upm.miw.apaw_practice.domain.models.football.Stadium;
import org.springframework.stereotype.Repository;
import java.util.stream.Stream;

@Repository
public interface LearnerPersistence {
    Stream<es.upm.miw.apaw_practice.domain.models.Class.Learner> readAll();

    Learner readByName(String name);

    void update(Learner learner);
}