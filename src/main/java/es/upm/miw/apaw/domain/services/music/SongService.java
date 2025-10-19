package es.upm.miw.apaw.domain.services.music;

import es.upm.miw.apaw.domain.models.music.Song;
import es.upm.miw.apaw.domain.persistenceports.music.SongPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    private final SongPersistence songPersistence;

    @Autowired
    public SongService(SongPersistence songPersistence) {
        this.songPersistence = songPersistence;
    }

    public void create(Song song) {
        this.songPersistence.create(song);
    }
}
