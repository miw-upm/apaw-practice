package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos.AlbumRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.AlbumEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_manager.Album;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_manager.AlbumPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("albumPersistence")
public class AlbumPersistenceMongodb implements AlbumPersistence {
    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumPersistenceMongodb(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Album read(String albumTitle) {
        return this.albumRepository
                .findByAlbumTitle(albumTitle)
                .orElseThrow(() -> new NotFoundException("Album albumTitle: " + albumTitle))
                .toAlbum();
    }

    @Override
    public Stream<Album> readAll() {
        return this.albumRepository.findAll()
                .stream().map(AlbumEntity::toAlbum);
    }
}
