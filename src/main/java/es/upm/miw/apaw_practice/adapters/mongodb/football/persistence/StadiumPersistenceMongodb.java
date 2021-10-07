package es.upm.miw.apaw_practice.adapters.mongodb.football.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.football.daos.StadiumRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.football.entities.StadiumEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.football.Stadium;
import es.upm.miw.apaw_practice.domain.persistence_ports.football.StadiumPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("stadiumPersistence")
public class StadiumPersistenceMongodb implements StadiumPersistence {
    private final StadiumRepository stadiumRepository;

    @Autowired
    public StadiumPersistenceMongodb(StadiumRepository stadiumRepository) {
        this.stadiumRepository = stadiumRepository;
    }

    @Override
    public Stadium readByName(String name) {
        return this.stadiumRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Stadium with name: " + name))
                .toStadium();
    }

    @Override
    public void update(Stadium stadium) {
        StadiumEntity stadiumEntity = this.stadiumRepository
                .findByName(stadium.getName())
                .orElseThrow(() -> new NotFoundException("Stadium name:" + stadium.getName()));
        stadiumEntity.setName(stadium.getName());
        this.stadiumRepository.save(stadiumEntity);
    }
}
