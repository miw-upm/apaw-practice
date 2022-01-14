package es.upm.miw.apaw_practice.domain.services.training;

import es.upm.miw.apaw_practice.domain.persistence_ports.training.TrainingItemPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingItemService {

    private final TrainingItemPersistence trainingItemPersistence;

    @Autowired
    public TrainingItemService(TrainingItemPersistence trainingItemPersistence) {
        this.trainingItemPersistence = trainingItemPersistence;
    }

    public void delete(String name) {
        this.trainingItemPersistence.delete(name);
    }
}
