package es.upm.miw.apaw.adapters.mongodb.videoWebsite.persistence;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos.CommentRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.persistenceports.videoWebsite.CommentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("commentPersistence")
public class CommentPersistenceMongodb implements CommentPersistence {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentPersistenceMongodb(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void deleteById(UUID id) {
        if (!this.commentRepository.existsById(id)) {
            throw new NotFoundException("Comment not found with id: " + id);
        }
        this.commentRepository.deleteById(id);
    }
}