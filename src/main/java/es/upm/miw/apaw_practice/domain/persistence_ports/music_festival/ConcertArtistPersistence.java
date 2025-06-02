package es.upm.miw.apaw_practice.domain.persistence_ports.music_festival;

import es.upm.miw.apaw_practice.domain.models.music_festival.ConcertArtist;
import java.util.stream.Stream;
import org.springframework.stereotype.Repository;


@Repository
public interface ConcertArtistPersistence {
    Stream<ConcertArtist> findByNationality(String nationality);

    ConcertArtist readByName(String artistName);
}
