package es.upm.miw.apaw.domain.persistenceports.videogame;

import es.upm.miw.apaw.domain.models.videogame.LikeList;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LikeListPersistence {
  Boolean readSharedById(UUID id);
}
