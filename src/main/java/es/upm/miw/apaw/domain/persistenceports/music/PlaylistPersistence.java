package es.upm.miw.apaw.domain.persistenceports.music;

import es.upm.miw.apaw.domain.models.music.Playlist;

import java.util.stream.Stream;

public interface PlaylistPersistence {
    void delete(String code);
    void update(String code, Playlist playlist);
    Stream<String> findArtistNamesByLabel(String label);
}
