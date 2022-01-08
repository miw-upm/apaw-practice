package es.upm.miw.apaw_practice.domain.services.training;

import es.upm.miw.apaw_practice.domain.models.training.Participant;
import es.upm.miw.apaw_practice.domain.persistence_ports.training.ParticipantPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ParticipantService {

    private ParticipantPersistence participantPersistence;

    @Autowired
    public ParticipantService(ParticipantPersistence participantPersistence) {
        this.participantPersistence = participantPersistence;
    }

    public Stream<Participant> readAll() {
        return participantPersistence.readAll();
    }
}
