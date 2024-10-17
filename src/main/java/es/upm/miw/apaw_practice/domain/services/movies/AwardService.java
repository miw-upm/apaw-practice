package es.upm.miw.apaw_practice.domain.services.movies;

import es.upm.miw.apaw_practice.domain.models.movies.Actor;
import es.upm.miw.apaw_practice.domain.models.movies.Award;
import es.upm.miw.apaw_practice.domain.models.movies.Movie;
import es.upm.miw.apaw_practice.domain.persistence_ports.movies.ActorPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.movies.AwardPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.movies.MoviePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AwardService {

    private final AwardPersistence awardPersistence;
    private final ActorPersistence actorPersistence;
    private final MoviePersistence moviePersistence;

    @Autowired
    public AwardService(AwardPersistence awardPersistence, ActorPersistence actorPersistence, MoviePersistence moviePersistence) {
        this.awardPersistence = awardPersistence;
        this.actorPersistence = actorPersistence;
        this.moviePersistence = moviePersistence;
    }

    public List<String> findAwardNamesByActorRealName(String realName){
        Optional<Actor> optionalActor = this.actorPersistence.findByRealName(realName);

        if(optionalActor.isEmpty()){
            return List.of();
        }

        return this.moviePersistence.findByActorRealName(realName).stream()
                .map(Movie::getAwardWon)
                .filter(Objects::nonNull)
                .map(Award::getName)
                .distinct()
                .toList();
    }

    public void deleteAward(String nameCategoryYear) {
        this.awardPersistence.delete(nameCategoryYear);
    }
}
