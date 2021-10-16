package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.AlbumEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AlbumRepository extends MongoRepository<AlbumEntity, String> {
    Optional<AlbumEntity> findByAlbumTitle(String albumTitle);
}
