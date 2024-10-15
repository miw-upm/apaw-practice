package es.upm.miw.apaw_practice.adapters.mongodb.course.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.VideoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VideoRepository extends MongoRepository<VideoEntity, String> {
    Optional<VideoEntity> findByName(String name);
}
