package es.upm.miw.apaw_practice.domain.persistence_ports.training;

import es.upm.miw.apaw_practice.domain.models.training.TrainingItem;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface TrainingItemPersistence {
    TrainingItem updateName(String id, String name);
    Stream<TrainingItem> readAll();
}
