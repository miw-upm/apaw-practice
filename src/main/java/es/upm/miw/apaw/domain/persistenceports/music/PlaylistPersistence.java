package es.upm.miw.apaw.domain.persistenceports.music;

import es.upm.miw.apaw.domain.models.music.Playlist;

public interface PlaylistPersistence {
    void delete(String code);
    void update(String code, Playlist playlist);
}
