package es.upm.miw.apaw_practice.domain.services.training;

import es.upm.miw.apaw_practice.domain.models.training.Participant;
import es.upm.miw.apaw_practice.domain.persistence_ports.training.ParticipantPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {

    private final ParticipantPersistence participantPersistence;

    @Autowired
    public ParticipantService(ParticipantPersistence participantPersistence) {
        this.participantPersistence = participantPersistence;
    }

    public Participant read(String email) {
        return this.participantPersistence.readByEmail(email);
    }
}