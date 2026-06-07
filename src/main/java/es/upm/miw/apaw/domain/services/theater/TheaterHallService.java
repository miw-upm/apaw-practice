package es.upm.miw.apaw.domain.services.theater;

import es.upm.miw.apaw.domain.models.theater.TheaterHall;
import es.upm.miw.apaw.domain.persistenceports.theater.TheaterHallPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class TheaterHallService {

    private final TheaterHallPersistence theaterHallPersistence;

    @Autowired
    public TheaterHallService(TheaterHallPersistence theaterHallPersistence) {
        this.theaterHallPersistence = theaterHallPersistence;
    }

    public TheaterHall update(String hallCode, TheaterHall theaterHall) {
        return this.theaterHallPersistence.update(hallCode, theaterHall);
    }

    public Stream<TheaterHall> findByMinCapacity(Integer minCapacity) {
        return this.theaterHallPersistence.findByMinCapacity(minCapacity);
    }
}
