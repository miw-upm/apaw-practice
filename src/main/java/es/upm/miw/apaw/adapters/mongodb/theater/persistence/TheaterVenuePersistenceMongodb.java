package es.upm.miw.apaw.adapters.mongodb.theater.persistence;

import es.upm.miw.apaw.adapters.mongodb.theater.daos.TheaterHallRepository;
import es.upm.miw.apaw.adapters.mongodb.theater.daos.TheaterVenueRepository;
import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterHallEntity;
import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterVenueEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.theater.TheaterHall;
import es.upm.miw.apaw.domain.models.theater.TheaterVenue;
import es.upm.miw.apaw.domain.persistenceports.theater.TheaterVenuePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("theaterVenuePersistence")
public class TheaterVenuePersistenceMongodb implements TheaterVenuePersistence {

    private final TheaterVenueRepository theaterVenueRepository;
    private final TheaterHallRepository theaterHallRepository;

    @Autowired
    public TheaterVenuePersistenceMongodb(
            TheaterVenueRepository theaterVenueRepository,
            TheaterHallRepository theaterHallRepository) {
        this.theaterVenueRepository = theaterVenueRepository;
        this.theaterHallRepository = theaterHallRepository;
    }

    @Override
    public TheaterVenue create(TheaterVenue theaterVenue) {
        TheaterVenueEntity entity = new TheaterVenueEntity(theaterVenue);
        entity.setVenueHalls(toTheaterHallEntityList(theaterVenue.getVenueHalls()));
        return this.theaterVenueRepository.save(entity).toTheaterVenue();
    }

    @Override
    public TheaterVenue read(String venueCode) {
        return this.theaterVenueRepository.findByVenueCode(venueCode)
                .orElseThrow(() -> new NotFoundException("TheaterVenue venueCode: " + venueCode))
                .toTheaterVenue();
    }

    @Override
    public TheaterVenue update(String venueCode, TheaterVenue theaterVenue) {
        TheaterVenueEntity entity = this.theaterVenueRepository.findByVenueCode(venueCode)
                .orElseThrow(() -> new NotFoundException("TheaterVenue venueCode: " + venueCode));
        entity.fromTheaterVenue(theaterVenue);
        entity.setVenueHalls(toTheaterHallEntityList(theaterVenue.getVenueHalls()));
        return this.theaterVenueRepository.save(entity).toTheaterVenue();
    }

    private List<TheaterHallEntity> toTheaterHallEntityList(List<TheaterHall> halls) {
        if (halls == null || halls.isEmpty()) {
            return List.of();
        }
        return halls.stream()
                .map(hall -> {
                    Optional<TheaterHallEntity> existing = theaterHallRepository.findByHallCode(hall.getHallCode());
                    if (existing.isPresent()) {
                        return existing.get();
                    }
                    TheaterHallEntity entity = new TheaterHallEntity();
                    entity.fromTheaterHall(hall);
                    return theaterHallRepository.save(entity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Stream<TheaterVenue> readAll() {
        return this.theaterVenueRepository.findAll().stream()
                .map(TheaterVenueEntity::toTheaterVenue);
    }

    @Override
    public boolean existsByVenueCode(String venueCode) {
        return this.theaterVenueRepository.findByVenueCode(venueCode).isPresent();
    }

    @Override
    public Stream<TheaterHall> findHallsByVenueCode(String venueCode) {
        return this.theaterVenueRepository.findByVenueCode(venueCode)
                .orElseThrow(() -> new NotFoundException("TheaterVenue venueCode: " + venueCode))
                .getVenueHalls()
                .stream()
                .map(TheaterHallEntity::toTheaterHall);
    }
}
