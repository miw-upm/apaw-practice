package es.upm.miw.apaw.adapters.mongodb.football.persistence;

import es.upm.miw.apaw.adapters.mongodb.football.daos.StadiumRepository;
import es.upm.miw.apaw.adapters.mongodb.football.entities.StadiumEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.football.Stadium;
import es.upm.miw.apaw.domain.persistenceports.football.StadiumPersistence;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("stadiumPersistence")
public class StadiumPersistenceMongodb implements StadiumPersistence {

    private final StadiumRepository stadiumRepository;
    private static final String STADIUM_NAME = "Stadium name: ";

    public StadiumPersistenceMongodb(StadiumRepository stadiumRepository) {
        this.stadiumRepository = stadiumRepository;
    }

    @Override
    public Optional<Stadium> findByOfficialName(String name) {
        return this.stadiumRepository.findByOfficialName(name)
                .map(StadiumEntity::toStadium);
    }

    @Override
    public List<Stadium> readAll() {
        return this.stadiumRepository.findAll().stream()
                .map(StadiumEntity::toStadium)
                .toList();
    }
}
