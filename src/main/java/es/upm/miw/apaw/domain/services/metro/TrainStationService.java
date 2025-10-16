package es.upm.miw.apaw.domain.services.metro;

import org.springframework.beans.factory.annotation.Autowired;
import es.upm.miw.apaw.domain.persistenceports.metro.TrainStationPersistence;
import org.springframework.stereotype.Service;


@Service
public class TrainStationService {

    private final TrainStationPersistence trainStationPersistence;

    @Autowired
    public TrainStationService(TrainStationPersistence trainStationPersistence) {
        this.trainStationPersistence = trainStationPersistence;
    }

    public Integer readCapacityByName (String name) {
        return this.trainStationPersistence.readCapacityByName(name);
    }
}