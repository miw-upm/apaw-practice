package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.RideEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos.RideRepository;
import es.upm.miw.apaw_practice.domain.models.theme_park.Ride;
import es.upm.miw.apaw_practice.domain.persistence_ports.theme_park.RidePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;


@Repository("ridePersistence")
public class RidePersistenceMongodb implements RidePersistence {

    private final RideRepository rideRepository;

    @Autowired
    public RidePersistenceMongodb(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    @Override
    public Stream<Ride> findByThemeAndMaxCapacityLessThan(String theme, Integer maxCapacity) {
        return this.rideRepository.findAll().stream()
                .filter(ride -> theme.equals(ride.getTheme()))
                .filter(ride -> ride.getMaxCapacity() < maxCapacity)
                .map(RideEntity::toRide);
    }


}
