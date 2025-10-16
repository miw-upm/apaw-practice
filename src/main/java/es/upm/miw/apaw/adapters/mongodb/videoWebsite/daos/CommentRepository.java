package es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities.CommentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CommentRepository extends MongoRepository<CommentEntity, UUID> {
}
