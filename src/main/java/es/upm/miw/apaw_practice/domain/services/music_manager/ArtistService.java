package es.upm.miw.apaw_practice.domain.services.music_manager;

import es.upm.miw.apaw_practice.domain.models.music_manager.Artist;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_manager.ArtistPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {

    private final ArtistPersistence artistPersistence;

    @Autowired
    public ArtistService(ArtistPersistence artistPersistence) {
        this.artistPersistence = artistPersistence;
    }

    public Artist update(String id, Artist artist) {
        return this.artistPersistence.update(id, artist);
    }
}
