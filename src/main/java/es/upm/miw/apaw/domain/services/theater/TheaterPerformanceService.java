package es.upm.miw.apaw.domain.services.theater;

import es.upm.miw.apaw.domain.models.theater.TheaterPerformance;
import es.upm.miw.apaw.domain.persistenceports.theater.TheaterPerformancePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Stream;

@Service
public class TheaterPerformanceService {

    private final TheaterPerformancePersistence theaterPerformancePersistence;

    @Autowired
    public TheaterPerformanceService(TheaterPerformancePersistence theaterPerformancePersistence) {
        this.theaterPerformancePersistence = theaterPerformancePersistence;
    }

    public Stream<TheaterPerformance> findByMinDate(LocalDate minDate) {
        return this.theaterPerformancePersistence.findByMinDate(minDate);
    }
}
