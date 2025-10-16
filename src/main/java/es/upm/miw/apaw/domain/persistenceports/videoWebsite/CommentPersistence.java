package es.upm.miw.apaw.domain.persistenceports.videoWebsite;

import es.upm.miw.apaw.domain.models.videoWebsite.Comment;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface CommentPersistence {
    void deleteById(UUID id);
    Comment findById(UUID id);
    Comment create(Comment comment);
    Stream<Comment> findAll();
}
