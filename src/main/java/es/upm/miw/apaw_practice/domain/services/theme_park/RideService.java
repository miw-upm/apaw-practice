package es.upm.miw.apaw_practice.domain.services.theme_park;

import es.upm.miw.apaw_practice.domain.models.theme_park.Ride;
import es.upm.miw.apaw_practice.domain.persistence_ports.theme_park.RidePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class RideService {

    private final RidePersistence ridePersistence;

    @Autowired
    public RideService(RidePersistence ridePersistence) {
        this.ridePersistence = ridePersistence;
    }

    public Stream<Ride> findByThemeAndMaxCapacityLessThan(String theme, Integer maxCapacity) {
        return this.ridePersistence.findByThemeAndMaxCapacityLessThan(theme, maxCapacity);
    }
}
