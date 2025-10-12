package es.upm.miw.apaw.domain.services.warehouse;

import es.upm.miw.apaw.domain.models.warehouse.Location;
import es.upm.miw.apaw.domain.persistenceports.warehouse.LocationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class LocationService {

    private final LocationPersistence locationPersistence;

    @Autowired
    public LocationService(LocationPersistence locationPersistence) {
        this.locationPersistence = locationPersistence;
    }

    public Stream<Location> readAll() {
        return this.locationPersistence.readAll();
    }

}
