package es.upm.miw.apaw.adapters.mongodb.theater.persistence;

import es.upm.miw.apaw.adapters.mongodb.theater.daos.TheaterArtistRepository;
import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterArtistEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.theater.TheaterArtist;
import es.upm.miw.apaw.domain.persistenceports.theater.TheaterArtistPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("theaterArtistPersistence")
public class TheaterArtistPersistenceMongodb implements TheaterArtistPersistence {

    private final TheaterArtistRepository theaterArtistRepository;

    @Autowired
    public TheaterArtistPersistenceMongodb(TheaterArtistRepository theaterArtistRepository) {
        this.theaterArtistRepository = theaterArtistRepository;
    }

    @Override
    public TheaterArtist create(TheaterArtist theaterArtist) {
        return this.theaterArtistRepository.save(new TheaterArtistEntity(theaterArtist)).toTheaterArtist();
    }

    @Override
    public TheaterArtist read(String artistCode) {
        return this.theaterArtistRepository.findByArtistCode(artistCode)
                .orElseThrow(() -> new NotFoundException("TheaterArtist artistCode: " + artistCode))
                .toTheaterArtist();
    }

    @Override
    public TheaterArtist update(String artistCode, TheaterArtist theaterArtist) {
        TheaterArtistEntity entity = this.theaterArtistRepository.findByArtistCode(artistCode)
                .orElseThrow(() -> new NotFoundException("TheaterArtist artistCode: " + artistCode));
        entity.fromTheaterArtist(theaterArtist);
        return this.theaterArtistRepository.save(entity).toTheaterArtist();
    }

    @Override
    public Stream<TheaterArtist> readAll() {
        return this.theaterArtistRepository.findAll().stream()
                .map(TheaterArtistEntity::toTheaterArtist);
    }

    @Override
    public boolean existsByArtistCode(String artistCode) {
        return this.theaterArtistRepository.findByArtistCode(artistCode).isPresent();
    }
}
