package es.upm.miw.apaw_practice.domain.persistence_ports.training;

import es.upm.miw.apaw_practice.domain.models.training.Participant;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ParticipantPersistence {
    Stream<Participant> readAll();
}
