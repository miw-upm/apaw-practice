package es.upm.miw.apaw_practice.domain.services.training;

import es.upm.miw.apaw_practice.domain.models.training.TrainingItem;
import es.upm.miw.apaw_practice.domain.persistence_ports.training.TrainingItemPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class TrainingItemService {

    private final TrainingItemPersistence trainingItemPersistence;

    @Autowired
    public TrainingItemService(TrainingItemPersistence trainingItemPersistence) {
        this.trainingItemPersistence = trainingItemPersistence;
    }

    public TrainingItem updateName(String id, String name) {
        return this.trainingItemPersistence.updateName(id,name);
    }

    public Stream<TrainingItem> readAll() {
        return this.trainingItemPersistence.readAll();
    }
}
