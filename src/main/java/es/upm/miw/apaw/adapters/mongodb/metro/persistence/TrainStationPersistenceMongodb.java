package es.upm.miw.apaw.adapters.mongodb.metro.persistence;
import es.upm.miw.apaw.adapters.mongodb.metro.daos.TrainStationRepository;
import es.upm.miw.apaw.adapters.mongodb.metro.entities.TrainStationEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.metro.TrainStation;
import es.upm.miw.apaw.domain.persistenceports.metro.TrainStationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("trainStationPersistence")
public class TrainStationPersistenceMongodb implements TrainStationPersistence {

    private final TrainStationRepository trainStationRepository;

    @Autowired
    public TrainStationPersistenceMongodb(TrainStationRepository trainStationRepository) {
        this.trainStationRepository = trainStationRepository;
    }

    @Override
    public Integer readCapacityByName(String name) {
        return this.trainStationRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Train Station Name: " + name))
                .getCapacity();
    }

    @Override
    public Stream<TrainStation> findAll() {
        return this.trainStationRepository.findAll().stream()
                .map(TrainStationEntity::toTrainStation);
    }

}
