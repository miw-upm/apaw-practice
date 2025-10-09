package es.upm.miw.apaw.adapters.mongodb.videogame.daos;


import es.upm.miw.apaw.adapters.mongodb.videogame.entities.LikeListEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface LikeListRepository extends MongoRepository<LikeListEntity, UUID> {
}
