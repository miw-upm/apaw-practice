package es.upm.miw.apaw.domain.persistenceports.airport;

import es.upm.miw.apaw.domain.models.airport.Airline;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface AirlinePersistence {
    void delete(String name);
    Stream<String> readByPlaneModel(String planeModel);
    boolean existsName(String name);
}
