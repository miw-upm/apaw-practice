package es.upm.miw.apaw_practice.adapters.mongodb.shop.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.TagEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagRepository extends MongoRepository<TagEntity, String> {
}
