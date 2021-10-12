package es.upm.miw.apaw_practice.domain.services.music_manager;

import es.upm.miw.apaw_practice.domain.persistence_ports.music_manager.SongPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    private final SongPersistence songPersistence;

    @Autowired
    public SongService(SongPersistence songPersistence) {
        this.songPersistence = songPersistence;
    }

    public void delete(String id) {
        this.songPersistence.delete(id);
    }

}
