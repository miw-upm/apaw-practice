package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos.ConcertArtistRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_festival.ConcertArtist;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_festival.ConcertArtistPersistence;
import org.springframework.stereotype.Repository;

@Repository("concertArtistPersistence")
public class ConcertArtistPersistenceMongodb implements ConcertArtistPersistence {
    private final ConcertArtistRepository concertArtistRepository;

    public ConcertArtistPersistenceMongodb(ConcertArtistRepository concertArtistRepository) {
        this.concertArtistRepository = concertArtistRepository;
    }

    @Override
    public ConcertArtist readByName(String artistName) {
        return this.concertArtistRepository.findByName(artistName)
                .orElseThrow(() -> new NotFoundException("ConcertArtist name:"+artistName))
                .toConcertArtist();
    }
}
