package es.upm.miw.apaw.domain.persistenceports.music;

import es.upm.miw.apaw.domain.models.music.Song;

public interface SongPersistence {
    void create(Song song);
}
