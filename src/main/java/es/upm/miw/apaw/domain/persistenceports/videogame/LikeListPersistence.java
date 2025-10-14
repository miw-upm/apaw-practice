package es.upm.miw.apaw.domain.persistenceports.videogame;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LikeListPersistence {
    Boolean readSharedById(UUID id);
}
