package es.upm.miw.apaw.domain.persistenceports.metro;
import es.upm.miw.apaw.domain.models.metro.TrainStation;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;


@Repository
public interface TrainStationPersistence {
    Integer readCapacityByName(String name);

    Stream<TrainStation> findAll();
}

