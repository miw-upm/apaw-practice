package es.upm.miw.apaw.domain.services.videoWebsite;

import es.upm.miw.apaw.domain.persistenceports.videoWebsite.CommentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentService {

    private final CommentPersistence commentPersistence;

    @Autowired
    public CommentService(CommentPersistence commentPersistence) {
        this.commentPersistence = commentPersistence;
    }

    public void deleteById(UUID id) {
        this.commentPersistence.deleteById(id);
    }
}
