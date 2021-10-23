package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos.ActorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos.FilmRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos.ScreenRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ActorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.FilmEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import es.upm.miw.apaw_practice.domain.models.cinema.Film;
import es.upm.miw.apaw_practice.domain.models.cinema.Screen;
import es.upm.miw.apaw_practice.domain.models.cinema.Spectator;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.ActorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository("actorPersistence")
public class ActorPersistenceMongodb implements ActorPersistence {

    private final ActorRepository actorRepository;
    private final FilmRepository filmRepository;
    private final ScreenRepository screenRepository;

    @Autowired
    public ActorPersistenceMongodb(ActorRepository actorRepository, FilmRepository filmRepository, ScreenRepository screenRepository) {
        this.actorRepository = actorRepository;
        this.filmRepository = filmRepository;
        this.screenRepository = screenRepository;
    }

    @Override
    public Actor create(Actor actor) {
        return this.actorRepository
                .save(new ActorEntity(actor))
                .toActor();
    }

    @Override
    public Actor update(Integer age, Actor actor) {
        ActorEntity actorEntity = this.actorRepository
                .findByNameAndFamilyName(actor.getName(), actor.getFamilyName())
                .orElseThrow(() -> new NotFoundException("Actor : " + actor.getName() + " " + actor.getFamilyName()));
        actorEntity.fromActor(actor);
        return this.actorRepository
                .save(actorEntity)
                .toActor();
    }

    @Override
    public Actor read(Actor actor) {
        return this.actorRepository
                .findByNameAndFamilyName(actor.getName(), actor.getFamilyName())
                .orElseThrow(() -> new NotFoundException("Actor : " + actor.getName() + " " + actor.getFamilyName()))
                .toActor();
    }

    private List<ActorEntity> findActorsOlderThanAge(Integer age){
        return this.actorRepository.findAll().stream()
                .filter(actors -> actors.getAge() > age)
                .collect(Collectors.toList());
    }

    private boolean checkIfFilmContainActor(List<ActorEntity> actorEntityList, FilmEntity film){
        for(ActorEntity actorEntity : actorEntityList){
            if(film.getActors().stream().anyMatch(actor -> actorEntity.getName().equals(actor.getName()) && actorEntity.getFamilyName().equals(actor.getFamilyName()))){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> getSpectatorsNamesByAge(Integer age) {
        List<Spectator> spectatorsList = new ArrayList<>();
        List<ActorEntity> actorList = findActorsOlderThanAge(age);
        List<FilmEntity> filmList = this.filmRepository.findAll();

        for (FilmEntity film : filmList) {
            if(checkIfFilmContainActor(actorList, film)){
                spectatorsList.addAll(film.toFilm().getScreen().getSpectators());
            }
        }
        List<String> spectatorsNameList = spectatorsList.stream().map(Spectator::getName).collect(Collectors.toList());
        Set<String> stringSet = new HashSet<>(spectatorsNameList);
        spectatorsNameList.clear();
        spectatorsNameList.addAll(stringSet);
        return spectatorsNameList;
    }

}