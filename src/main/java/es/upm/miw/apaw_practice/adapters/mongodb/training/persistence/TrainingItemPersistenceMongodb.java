package es.upm.miw.apaw_practice.adapters.mongodb.training.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.training.daos.TrainingItemRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.TrainingItemEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.training.TrainingItem;
import es.upm.miw.apaw_practice.domain.persistence_ports.training.TrainingItemPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("TrainingItemPersistence")
public class TrainingItemPersistenceMongodb implements TrainingItemPersistence {
    private final TrainingItemRepository trainingItemRepository;

    @Autowired
    public TrainingItemPersistenceMongodb(TrainingItemRepository trainingItemRepository) {
        this.trainingItemRepository = trainingItemRepository;
    }

    @Override
    public Stream<TrainingItem> readAll() {
        return this.trainingItemRepository.findAll().stream()
                .map(TrainingItemEntity::toTrainingItem);
    }

    @Override
    public TrainingItem updateName(String id, String name) {
        TrainingItemEntity trainingItemEntity = this.trainingItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("TrainingItem id: " + id));
        trainingItemEntity.setName(name);
        return this.trainingItemRepository.save(trainingItemEntity).toTrainingItem();
    }
}
