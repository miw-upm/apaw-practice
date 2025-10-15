package es.upm.miw.apaw.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw.adapters.mongodb.videogame.entities.VideogameEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface VideogameRepository extends MongoRepository<VideogameEntity, UUID> {

    void deleteByName(String name);

    @Query("{ 'genreEntity.$id': ?0 }")
    List<VideogameEntity> findByGenreEntityId(UUID genreId);
}
