package es.upm.miw.apaw.domain.services.theater;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.theater.TheaterArtist;
import es.upm.miw.apaw.domain.persistenceports.theater.TheaterArtistPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterArtistService {

    private final TheaterArtistPersistence theaterArtistPersistence;

    @Autowired
    public TheaterArtistService(TheaterArtistPersistence theaterArtistPersistence) {
        this.theaterArtistPersistence = theaterArtistPersistence;
    }

    public TheaterArtist create(TheaterArtist theaterArtist) {
        if (this.theaterArtistPersistence.existsByArtistCode(theaterArtist.getArtistCode())) {
            throw new ConflictException("TheaterArtist artistCode: " + theaterArtist.getArtistCode());
        }
        return this.theaterArtistPersistence.create(theaterArtist);
    }

    public void delete(String artistCode) {
        this.theaterArtistPersistence.delete(artistCode);
    }

    public TheaterArtist patchActive(String artistCode, Boolean artistActive) {
        return this.theaterArtistPersistence.patchActive(artistCode, artistActive);
    }
}
