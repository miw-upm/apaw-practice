package es.upm.miw.apaw_practice.adapters.mongodb.training.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.training.daos.TrainingItemRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.training.TrainingItemPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("TrainingItemPersistence")
public class TrainingItemPersistenceMongodb implements TrainingItemPersistence {
    private final TrainingItemRepository trainingItemRepository;

    @Autowired
    public TrainingItemPersistenceMongodb(TrainingItemRepository trainingItemRepository) {
        this.trainingItemRepository = trainingItemRepository;
    }

    @Override
    public void delete(String name) {
        this.trainingItemRepository.deleteByName(name);
    }
}
