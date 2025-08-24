package es.upm.miw.apaw.adapters.mongodb.shop.daos;

import es.upm.miw.apaw.adapters.mongodb.shop.entities.TagEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface TagRepository extends MongoRepository<TagEntity, UUID> {
    Optional<TagEntity> findByName(String name);

    int deleteByName(String name);
}
