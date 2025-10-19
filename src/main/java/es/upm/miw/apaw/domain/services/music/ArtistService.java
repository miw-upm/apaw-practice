package es.upm.miw.apaw.domain.services.music;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.music.Artist;
import es.upm.miw.apaw.domain.persistenceports.music.ArtistPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {

    private final ArtistPersistence artistPersistence;

    @Autowired
    public ArtistService(ArtistPersistence artistPersistence) {
        this.artistPersistence = artistPersistence;
    }

    public Artist readByName(String name) {
        return this.artistPersistence.readByName(name)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Artist not found: " + name));
    }
}
