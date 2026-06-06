package es.upm.miw.apaw.domain.persistenceports.theater;

import es.upm.miw.apaw.domain.models.theater.TheaterArtist;
import es.upm.miw.apaw.domain.models.theater.TheaterPerformance;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface TheaterPerformancePersistence {

    TheaterPerformance create(TheaterPerformance theaterPerformance);

    TheaterPerformance read(String performanceCode);

    TheaterPerformance update(String performanceCode, TheaterPerformance theaterPerformance);

    Stream<TheaterPerformance> readAll();

    boolean existsByPerformanceCode(String performanceCode);

    Stream<TheaterArtist> findArtistsByPerformanceCode(String performanceCode);
}
