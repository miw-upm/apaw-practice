package es.upm.miw.apaw.domain.persistenceports.videoWebsite;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentPersistence {
    void deleteById(UUID id);
}
