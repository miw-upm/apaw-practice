package es.upm.miw.apaw.domain.persistenceports.theater;

import es.upm.miw.apaw.domain.models.theater.TheaterArtist;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface TheaterArtistPersistence {

    TheaterArtist create(TheaterArtist theaterArtist);

    TheaterArtist read(String artistCode);

    TheaterArtist update(String artistCode, TheaterArtist theaterArtist);

    Stream<TheaterArtist> readAll();

    boolean existsByArtistCode(String artistCode);
}
