package es.upm.miw.apaw.domain.persistenceports.metro;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainStationPersistence {
    Integer readCapacityByName(String name);
}

