package es.upm.miw.apaw.adapters.mongodb.metro.persistence;

import es.upm.miw.apaw.adapters.mongodb.metro.daos.TrainRepository;
import es.upm.miw.apaw.domain.persistenceports.metro.TrainPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("trainPersistence")
public class TrainPersistenceMongodb implements TrainPersistence {
    private final TrainRepository trainRepository;

    @Autowired
    public TrainPersistenceMongodb(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Override
    public void delete(UUID id) {
        this.trainRepository.deleteTrainById(id);
    }

}
