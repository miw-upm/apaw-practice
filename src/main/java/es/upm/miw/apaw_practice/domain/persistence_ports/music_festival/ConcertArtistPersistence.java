package es.upm.miw.apaw_practice.domain.persistence_ports.music_festival;

import es.upm.miw.apaw_practice.domain.models.music_festival.ConcertArtist;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertArtistPersistence {
    ConcertArtist readByName(String artistName);
}
