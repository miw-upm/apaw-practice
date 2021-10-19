package es.upm.miw.apaw_practice.domain.persistence_ports.music_manager;

import es.upm.miw.apaw_practice.domain.models.music_manager.Album;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface AlbumPersistence {
    Album read(String albumTitle);

    Stream<Album> readAll();
}
