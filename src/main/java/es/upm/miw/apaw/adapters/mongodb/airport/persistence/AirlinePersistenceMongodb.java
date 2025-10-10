package es.upm.miw.apaw.adapters.mongodb.airport.persistence;

import es.upm.miw.apaw.adapters.mongodb.airport.daos.AirlineRepository;
import es.upm.miw.apaw.domain.models.airport.Airline;
import es.upm.miw.apaw.domain.persistenceports.airport.AirlinePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("airlinePersistence")
public class AirlinePersistenceMongodb implements AirlinePersistence {

    private final AirlineRepository airlineRepository;

    @Autowired
    public AirlinePersistenceMongodb(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Override
    public void delete(String name) {
        this.airlineRepository.deleteByName(name);
    }

    @Override
    public boolean existsName(String name) {
        return this.airlineRepository.findByName(name).isPresent();
    }
}
