package es.upm.miw.apaw.adapters.mongodb.videoWebsite.persistence;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos.CommentRepository;
import es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities.*;
import es.upm.miw.apaw.domain.models.videoWebsite.*;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.persistenceports.videoWebsite.CommentPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("commentPersistence")
public class CommentPersistenceMongodb implements CommentPersistence {

    private final CommentRepository commentRepository;
    private static final String COMMENT_NOT_FOUND = "Comment Not Found with id: ";

    @Autowired
    public CommentPersistenceMongodb(CommentRepository commentRepository, UserRestClient userRestClient) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void deleteById(UUID id) {
        if (!this.commentRepository.existsById(id)) {
            throw new NotFoundException(COMMENT_NOT_FOUND + id);
        }
        this.commentRepository.deleteById(id);
    }

    @Override
    public Comment findById(UUID id) {
        CommentEntity entity = this.commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(COMMENT_NOT_FOUND + id));
        return entity.toComment();
    }

    public Comment create(Comment comment) {
        CommentEntity entity = new CommentEntity(comment);
        CommentEntity saved = this.commentRepository.save(entity);
        return saved.toComment();
    }

    public Stream<Comment> findAll() {
        return commentRepository.findAll()
                .stream()
                .map(CommentEntity::toComment);
    }




}