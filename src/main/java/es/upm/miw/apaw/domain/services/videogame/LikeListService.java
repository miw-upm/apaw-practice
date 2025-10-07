package es.upm.miw.apaw.domain.services.videogame;

import es.upm.miw.apaw.domain.models.videogame.LikeList;
import es.upm.miw.apaw.domain.persistenceports.videogame.LikeListPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Stream;

@Service
public class LikeListService {
    private final LikeListPersistence likeListPersistence;

    @Autowired
    public LikeListService(LikeListPersistence likeListPersistence) {
        this.likeListPersistence = likeListPersistence;
    }

    public boolean readSharedById(UUID id){
        return this.likeListPersistence.readSharedById(id);
    }
}
