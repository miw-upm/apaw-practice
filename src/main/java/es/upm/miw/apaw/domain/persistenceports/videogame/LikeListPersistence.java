package es.upm.miw.apaw.domain.persistenceports.videogame;

import es.upm.miw.apaw.domain.models.videogame.Videogame;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface LikeListPersistence {
    Boolean readSharedById(UUID id);

    Stream<Videogame> findVideogamesByUserId(UUID id);
}
