package es.upm.miw.apaw.domain.persistenceports.theater;

import es.upm.miw.apaw.domain.models.theater.TheaterHall;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface TheaterHallPersistence {

    TheaterHall create(TheaterHall theaterHall);

    TheaterHall read(String hallCode);

    TheaterHall update(String hallCode, TheaterHall theaterHall);

    Stream<TheaterHall> readAll();

    boolean existsByHallCode(String hallCode);
}
