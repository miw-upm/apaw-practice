package es.upm.miw.apaw_practice.adapters.mongodb.training.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.TrainingItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainingItemRepository extends MongoRepository<TrainingItemEntity, String> {
}
