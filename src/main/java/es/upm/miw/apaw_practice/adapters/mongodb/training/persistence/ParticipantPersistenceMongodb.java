package es.upm.miw.apaw_practice.adapters.mongodb.training.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.training.daos.ParticipantRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.training.ParticipantPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ParticipantPersistence")
public class ParticipantPersistenceMongodb implements ParticipantPersistence {
    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantPersistenceMongodb(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }
}
