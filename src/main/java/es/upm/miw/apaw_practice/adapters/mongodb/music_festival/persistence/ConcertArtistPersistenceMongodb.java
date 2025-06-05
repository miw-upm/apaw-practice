package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos.ConcertArtistRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.ConcertArtistEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_festival.ConcertArtist;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_festival.ConcertArtistPersistence;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("concertArtistPersistence")
public class ConcertArtistPersistenceMongodb implements ConcertArtistPersistence {
    private final ConcertArtistRepository concertArtistRepository;

    @Autowired
    public ConcertArtistPersistenceMongodb(ConcertArtistRepository concertArtistRepository) {
        this.concertArtistRepository = concertArtistRepository;
    }

    @Override
    public Stream<ConcertArtist> findByNationality(String nationality) {
        return this.concertArtistRepository.findAll().stream()
                .filter(artistEntity -> artistEntity.getNationality().contains(nationality))
                .map(ConcertArtistEntity::toConcertArtist);
    }

    @Override
    public ConcertArtist readByName(String artistName) {
        return this.concertArtistRepository.findByName(artistName)
                .orElseThrow(() -> new NotFoundException("ConcertArtist name:"+artistName))
                .toConcertArtist();
    }
}
