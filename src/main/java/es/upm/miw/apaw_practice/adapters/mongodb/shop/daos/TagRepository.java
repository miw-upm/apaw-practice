package es.upm.miw.apaw_practice.adapters.mongodb.shop.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.TagEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TagRepository extends MongoRepository<TagEntity, String> {
    Optional<TagEntity> findByName(String name);

    int deleteByName(String name);
}
