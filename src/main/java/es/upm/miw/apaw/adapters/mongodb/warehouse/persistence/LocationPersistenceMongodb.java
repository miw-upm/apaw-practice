package es.upm.miw.apaw.adapters.mongodb.warehouse.persistence;

import es.upm.miw.apaw.adapters.mongodb.warehouse.daos.LocationRepository;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.LocationEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.warehouse.Location;
import es.upm.miw.apaw.domain.persistenceports.warehouse.LocationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("locationPersistence")
public class LocationPersistenceMongodb implements LocationPersistence {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationPersistenceMongodb(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Stream<Location> readAll() {
        return this.locationRepository.findAll().stream()
                .map(LocationEntity::toLocation);
    }

    @Override
    public Location readByPosition (String position) {
        return this.locationRepository.findByPosition(position)
                .orElseThrow(() -> new NotFoundException("Location position " + position))
                .toLocation();
    }

}
