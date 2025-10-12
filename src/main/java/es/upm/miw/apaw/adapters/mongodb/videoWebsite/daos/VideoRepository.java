package es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities.VideoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface VideoRepository extends MongoRepository<VideoEntity, UUID> {
}
