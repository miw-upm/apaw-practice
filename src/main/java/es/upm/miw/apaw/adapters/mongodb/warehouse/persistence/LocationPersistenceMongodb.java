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
    public Location readByPosition(String position) {
        return this.locationRepository.findByPosition(position)
                .map(LocationEntity::toLocation)
                .orElseThrow(() -> new NotFoundException("Location position: " + position));
    }

    @Override
    public Location update(Location location) {
        LocationEntity entity = this.locationRepository.findByPosition(location.getPosition())
                .orElseThrow(() -> new NotFoundException("Location position: " + location.getPosition()));
        entity.setAvailability(location.getAvailability());
        return this.locationRepository.save(entity).toLocation();
    }

    @Override
    public void deleteByPosition(String position) {
        LocationEntity entity = this.locationRepository.findByPosition(position)
                .orElseThrow(() -> new NotFoundException("Location not found: " + position));

        this.locationRepository.delete(entity);
    }

}