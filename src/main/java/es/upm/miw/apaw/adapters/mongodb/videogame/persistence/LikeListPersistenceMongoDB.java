package es.upm.miw.apaw.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw.adapters.mongodb.videogame.daos.LikeListRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.persistenceports.videogame.LikeListPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("likeListPersistence")
public class LikeListPersistenceMongoDB implements LikeListPersistence {

    private final LikeListRepository likeListRepository;

    @Autowired
    public LikeListPersistenceMongoDB(LikeListRepository likeListRepository) {
        this.likeListRepository = likeListRepository;
    }

    @Override
    public Boolean readSharedById(UUID id) {
        return this.likeListRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id: " + id))
                .getShared();
    }
}
