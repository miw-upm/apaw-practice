package es.upm.miw.apaw_practice.adapters.mongodb.training.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.training.daos.ParticipantRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.ParticipantEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.training.Participant;
import es.upm.miw.apaw_practice.domain.persistence_ports.training.ParticipantPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("ParticipantPersistence")
public class ParticipantPersistenceMongodb implements ParticipantPersistence {
    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantPersistenceMongodb(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public Stream<Participant> readAll() {
        return this.participantRepository.findAll().stream()
                .map(ParticipantEntity::toParticipant);
    }

    @Override
    public Participant update(Participant participant) {
        ParticipantEntity participantEntity = this.participantRepository
                .findById(participant.getId())
                .orElseThrow(() -> new NotFoundException("Participant id: " + participant.getId()));
        participantEntity.fromParticipant(participant);
        return this.participantRepository
                .save(participantEntity)
                .toParticipant();
    }

    @Override
    public Participant readByDni(String dni) {
        return this.participantRepository
                .findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Participant dni: " + dni))
                .toParticipant();
    }
}
