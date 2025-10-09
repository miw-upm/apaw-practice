package es.upm.miw.apaw.domain.services.airport;

import es.upm.miw.apaw.domain.persistenceports.airport.AirlinePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirlineService {

    private final AirlinePersistence airlinePersistence;

    @Autowired
    public AirlineService(AirlinePersistence airlinePersistence) {
        this.airlinePersistence = airlinePersistence;
    }

    public void delete(String name) {
        this.airlinePersistence.delete(name);
    }
}
