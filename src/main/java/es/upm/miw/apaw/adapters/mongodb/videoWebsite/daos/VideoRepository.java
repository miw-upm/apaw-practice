package es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities.VideoEntity;
import es.upm.miw.apaw.domain.models.videoWebsite.Video;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.VideoStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface VideoRepository extends MongoRepository<VideoEntity, UUID> {
    List<VideoEntity> findByTitle(String title);
}
