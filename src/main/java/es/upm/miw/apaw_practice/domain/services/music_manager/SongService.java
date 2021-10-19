package es.upm.miw.apaw_practice.domain.services.music_manager;

import es.upm.miw.apaw_practice.domain.models.music_manager.Album;
import es.upm.miw.apaw_practice.domain.models.music_manager.Song;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_manager.AlbumPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_manager.SongPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class SongService {

    private final SongPersistence songPersistence;

    @Autowired
    public SongService(SongPersistence songPersistence) {
        this.songPersistence = songPersistence;
    }

    @Autowired
    AlbumPersistence albumPersistence;

    public void delete(String songTitle) {
        this.songPersistence.delete(songTitle);
    }

    public Stream<String> findSongTitlesByArtistFirstName(String firstName) {
        Stream<Album> albumList = this.albumPersistence.readAll()
                .filter(album -> album.getBand().getArtists().stream()
                        .anyMatch(artist -> artist.getFirstName().equals(firstName)));
        return albumList
                .flatMap(album -> album.getTracks().stream())
                .map(Song::getSongTitle);
    }
}

