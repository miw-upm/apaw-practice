package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos.AlbumRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_manager.Album;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_manager.AlbumPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("albumPersistence")
public class AlbumPersistenceMongodb implements AlbumPersistence {
    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumPersistenceMongodb(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Album read(String id) {
        return this.albumRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Album id: " + id))
                .toAlbum();
    }
}
