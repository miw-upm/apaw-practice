package es.upm.miw.apaw.domain.persistenceports.metro;

import es.upm.miw.apaw.domain.models.metro.Train;

import java.util.UUID;
import java.util.stream.Stream;

public interface TrainPersistence {
    void delete(UUID id);
    Stream<Train> findAll();
}
