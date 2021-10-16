package es.upm.miw.apaw_practice.domain.services.music_manager;

import es.upm.miw.apaw_practice.domain.models.music_manager.Album;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_manager.AlbumPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

    private final AlbumPersistence albumPersistence;

    @Autowired
    public AlbumService(AlbumPersistence albumPersistence) {
        this.albumPersistence = albumPersistence;
    }

    public Album read(String albumTitle) {
        return this.albumPersistence.read(albumTitle);
    }
}
