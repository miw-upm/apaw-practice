package es.upm.miw.apaw.domain.services.metro;

import es.upm.miw.apaw.domain.persistenceports.metro.TrainPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TrainService {

    private final TrainPersistence trainPersistence;

    @Autowired
    public TrainService(TrainPersistence trainPersistence) {
        this.trainPersistence = trainPersistence;
    }

    public void delete(UUID id) {
        this.trainPersistence.delete(id);
    }
}