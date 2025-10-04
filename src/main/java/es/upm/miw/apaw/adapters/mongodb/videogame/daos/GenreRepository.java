package es.upm.miw.apaw.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw.adapters.mongodb.videogame.entities.GenreEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.UUID;

public interface GenreRepository extends MongoRepository<GenreEntity, UUID> {
}

