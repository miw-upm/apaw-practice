package es.upm.miw.apaw.domain.persistenceports.airport;

import org.springframework.stereotype.Repository;

@Repository
public interface AirlinePersistence {
    void delete(String name);
    boolean existsName(String name);
}
