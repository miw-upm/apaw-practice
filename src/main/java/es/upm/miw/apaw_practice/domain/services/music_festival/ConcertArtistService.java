package es.upm.miw.apaw_practice.domain.services.music_festival;

import es.upm.miw.apaw_practice.domain.models.music_festival.ConcertArtist;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_festival.ConcertArtistPersistence;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConcertArtistService {

    private final ConcertArtistPersistence concertArtistPersistence;

    @Autowired
    public ConcertArtistService(ConcertArtistPersistence concertArtistPersistence) {
        this.concertArtistPersistence = concertArtistPersistence;
    }

    public Stream<ConcertArtist> findByNationality(String nationality) {
        return this.concertArtistPersistence.findByNationality(nationality);
    }
}