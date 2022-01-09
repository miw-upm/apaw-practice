package es.upm.miw.apaw_practice.domain.services.training;

import es.upm.miw.apaw_practice.domain.models.training.Participant;
import es.upm.miw.apaw_practice.domain.models.training.ParticipantEmailUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.training.ParticipantPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ParticipantService {

    private final ParticipantPersistence participantPersistence;

    @Autowired
    public ParticipantService(ParticipantPersistence participantPersistence) {
        this.participantPersistence = participantPersistence;
    }

    public Stream<Participant> readAll() {
        return participantPersistence.readAll();
    }

    public void updateEmail(List<ParticipantEmailUpdating> participantEmailUpdatingList) {
        participantEmailUpdatingList.stream()
                .map(participantNewEmail -> {
                    Participant participant = this.participantPersistence.readByDni(participantNewEmail.getDni());
                    participant.setEmail(participantNewEmail.getEmail());
                    return participant;
                })
                .forEach(this.participantPersistence::update);
    }
}
