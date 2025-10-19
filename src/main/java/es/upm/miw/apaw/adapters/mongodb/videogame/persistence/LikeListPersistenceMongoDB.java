package es.upm.miw.apaw.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw.adapters.mongodb.videogame.daos.LikeListRepository;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.CompanyEntity;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.LikeListEntity;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.VideogameEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.videogame.Company;
import es.upm.miw.apaw.domain.models.videogame.LikeList;
import es.upm.miw.apaw.domain.models.videogame.Videogame;
import es.upm.miw.apaw.domain.persistenceports.videogame.LikeListPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

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


    @Override
    public Stream<Videogame> findVideogamesByUserId(UUID userId) {
        return this.likeListRepository.findByUserId(userId).stream() // devuelve List<LikeListEntity>
                .filter(Objects::nonNull)
                .flatMap(likeList -> {
                    if (likeList.getGamesLikedEntity() == null) return Stream.empty();
                    return likeList.getGamesLikedEntity().stream(); // Stream<VideogameEntity>
                })
                .filter(Objects::nonNull)
                .map(VideogameEntity::toVideogame); // Stream<Videogame>
    }

    @Override
    public Stream<LikeList> readAll() {
        return this.likeListRepository
                .findAll().stream()
                .map(LikeListEntity::toLikeList);


    }
}
