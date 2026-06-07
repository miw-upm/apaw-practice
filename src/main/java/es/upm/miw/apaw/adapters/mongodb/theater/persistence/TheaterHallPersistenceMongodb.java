package es.upm.miw.apaw.adapters.mongodb.theater.persistence;

import es.upm.miw.apaw.adapters.mongodb.theater.daos.TheaterHallRepository;
import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterHallEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.theater.TheaterHall;
import es.upm.miw.apaw.domain.persistenceports.theater.TheaterHallPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("theaterHallPersistence")
public class TheaterHallPersistenceMongodb implements TheaterHallPersistence {

    private final TheaterHallRepository theaterHallRepository;

    @Autowired
    public TheaterHallPersistenceMongodb(TheaterHallRepository theaterHallRepository) {
        this.theaterHallRepository = theaterHallRepository;
    }

    @Override
    public TheaterHall create(TheaterHall theaterHall) {
        return this.theaterHallRepository.save(new TheaterHallEntity(theaterHall)).toTheaterHall();
    }

    @Override
    public TheaterHall read(String hallCode) {
        return this.theaterHallRepository.findByHallCode(hallCode)
                .orElseThrow(() -> new NotFoundException("TheaterHall hallCode: " + hallCode))
                .toTheaterHall();
    }

    @Override
    public TheaterHall update(String hallCode, TheaterHall theaterHall) {
        TheaterHallEntity entity = this.theaterHallRepository.findByHallCode(hallCode)
                .orElseThrow(() -> new NotFoundException("TheaterHall hallCode: " + hallCode));
        entity.fromTheaterHall(theaterHall);
        return this.theaterHallRepository.save(entity).toTheaterHall();
    }

    @Override
    public Stream<TheaterHall> readAll() {
        return this.theaterHallRepository.findAll().stream()
                .map(TheaterHallEntity::toTheaterHall);
    }

    @Override
    public boolean existsByHallCode(String hallCode) {
        return this.theaterHallRepository.findByHallCode(hallCode).isPresent();
    }
}
