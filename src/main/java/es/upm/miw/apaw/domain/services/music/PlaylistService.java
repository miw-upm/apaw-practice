package es.upm.miw.apaw.domain.services.music;

import es.upm.miw.apaw.domain.models.music.Playlist;
import es.upm.miw.apaw.domain.persistenceports.music.PlaylistPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class PlaylistService {
    private final PlaylistPersistence playlistPersistence;

    @Autowired
    public PlaylistService(PlaylistPersistence playlistPersistence) {
        this.playlistPersistence = playlistPersistence;
    }

    public void delete(String code) {
        this.playlistPersistence.delete(code);
    }

    public void update(String code, Playlist playlist) {
        this.playlistPersistence.update(code, playlist);
    }

    public Stream<String> findArtistNamesByLabel(String label) {
        return this.playlistPersistence.findArtistNamesByLabel(label);
    }
}
