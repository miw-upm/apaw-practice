package es.upm.miw.apaw_practice.adapters.mongodb.Class.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.Class.daos.LearnerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.LearnerEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.Class.Learner;
import es.upm.miw.apaw_practice.domain.persistence_ports.Class.LearnerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("LearnerPersistence")
public class LearnerPersistenceMongodb implements LearnerPersistence {
    private final LearnerRepository learnerRepository;

    @Autowired
    public LearnerPersistenceMongodb(LearnerRepository learnerRepository) {
        this.learnerRepository = learnerRepository;
    }

    @Override
    public Stream<Learner> readAll() {
        return this.learnerRepository.findAll().stream()
                .map(LearnerEntity::toLearner);
    }

    @Override
    public Learner readByName(String name) {
        return this.learnerRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("not found"))
                .toLearner();
    }

    @Override
    public void update(Learner learner) {
        LearnerEntity learnerEntity = this.learnerRepository
                .findByName(learner.getName())
                .orElseThrow(() -> new NotFoundException("not found: " + learner.getName()));
        learnerEntity.setName(learner.getName());
        this.learnerRepository.save(learnerEntity);
    }
}
