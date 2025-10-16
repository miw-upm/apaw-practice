package es.upm.miw.apaw.domain.persistenceports.metro;

import java.util.UUID;

public interface TrainPersistence {
    void delete(UUID id);
}
