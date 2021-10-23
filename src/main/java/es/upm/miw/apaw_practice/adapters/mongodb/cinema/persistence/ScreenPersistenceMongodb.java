package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos.FilmRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos.ScreenRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ActorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.FilmEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import es.upm.miw.apaw_practice.domain.models.cinema.Screen;
import es.upm.miw.apaw_practice.domain.models.cinema.Spectator;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.ScreenPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository("screenPersistence")
public class ScreenPersistenceMongodb implements ScreenPersistence {

    private final ScreenRepository screenRepository;
    private final FilmRepository filmRepository;

    @Autowired
    public ScreenPersistenceMongodb(ScreenRepository screenRepository, FilmRepository filmRepository) {
        this.screenRepository = screenRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    public Screen readByNumber(Integer number) {
        return this.screenRepository
                .findByNumber(number)
                .orElseThrow(() -> new NotFoundException("Screen number:" + number))
                .toScreen();
    }

    @Override
    public Screen update(Screen screen, List<Spectator> spectatorList) {
        screen.setSpectators(spectatorList);
        return screen;
    }

    @Override
    public List<String> getActorsNameByScreenNumber(Integer number) {
        List<FilmEntity> filmList = this.filmRepository.findAll();
        List<String> actorListName = new ArrayList<>();
        for (FilmEntity film : filmList) {
            if(checkFilmScreen(number, film)){
                actorListName.addAll(film.getActors().stream()
                        .map(ActorEntity::getName)
                        .collect(Collectors.toList()));
            }
        }
        Set<String> stringSet = new HashSet<>(actorListName);
        actorListName.clear();
        actorListName.addAll(stringSet);
        return actorListName;
    }

    private boolean checkFilmScreen(Integer number, FilmEntity film) {
        return (film.getScreen().getNumber().equals(number));
    }
}
