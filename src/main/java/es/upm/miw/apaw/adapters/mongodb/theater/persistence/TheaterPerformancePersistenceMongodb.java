package es.upm.miw.apaw.adapters.mongodb.theater.persistence;

import es.upm.miw.apaw.adapters.mongodb.theater.daos.TheaterArtistRepository;
import es.upm.miw.apaw.adapters.mongodb.theater.daos.TheaterHallRepository;
import es.upm.miw.apaw.adapters.mongodb.theater.daos.TheaterPerformanceRepository;
import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterArtistEntity;
import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterHallEntity;
import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterPerformanceEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.theater.TheaterArtist;
import es.upm.miw.apaw.domain.models.theater.TheaterHall;
import es.upm.miw.apaw.domain.models.theater.TheaterPerformance;
import es.upm.miw.apaw.domain.persistenceports.theater.TheaterPerformancePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("theaterPerformancePersistence")
public class TheaterPerformancePersistenceMongodb implements TheaterPerformancePersistence {

    private final TheaterPerformanceRepository theaterPerformanceRepository;
    private final TheaterHallRepository theaterHallRepository;
    private final TheaterArtistRepository theaterArtistRepository;

    @Autowired
    public TheaterPerformancePersistenceMongodb(
            TheaterPerformanceRepository theaterPerformanceRepository,
            TheaterHallRepository theaterHallRepository,
            TheaterArtistRepository theaterArtistRepository) {
        this.theaterPerformanceRepository = theaterPerformanceRepository;
        this.theaterHallRepository = theaterHallRepository;
        this.theaterArtistRepository = theaterArtistRepository;
    }

    @Override
    public TheaterPerformance create(TheaterPerformance theaterPerformance) {
        TheaterPerformanceEntity entity = new TheaterPerformanceEntity(theaterPerformance);
        entity.setPerformanceHall(toTheaterHallEntity(theaterPerformance.getPerformanceHall()));
        entity.setPerformanceArtists(toTheaterArtistEntitySet(theaterPerformance.getPerformanceArtists()));
        return this.theaterPerformanceRepository.save(entity).toTheaterPerformance();
    }

    @Override
    public TheaterPerformance read(String performanceCode) {
        return this.theaterPerformanceRepository.findByPerformanceCode(performanceCode)
                .orElseThrow(() -> new NotFoundException("TheaterPerformance performanceCode: " + performanceCode))
                .toTheaterPerformance();
    }

    @Override
    public TheaterPerformance update(String performanceCode, TheaterPerformance theaterPerformance) {
        TheaterPerformanceEntity entity = this.theaterPerformanceRepository.findByPerformanceCode(performanceCode)
                .orElseThrow(() -> new NotFoundException("TheaterPerformance performanceCode: " + performanceCode));
        entity.fromTheaterPerformance(theaterPerformance);
        entity.setPerformanceHall(toTheaterHallEntity(theaterPerformance.getPerformanceHall()));
        entity.setPerformanceArtists(toTheaterArtistEntitySet(theaterPerformance.getPerformanceArtists()));
        return this.theaterPerformanceRepository.save(entity).toTheaterPerformance();
    }

    private TheaterHallEntity toTheaterHallEntity(TheaterHall hall) {
        if (hall == null) {
            return null;
        }
        Optional<TheaterHallEntity> existing = theaterHallRepository.findByHallCode(hall.getHallCode());
        if (existing.isPresent()) {
            return existing.get();
        }
        TheaterHallEntity entity = new TheaterHallEntity();
        entity.fromTheaterHall(hall);
        return theaterHallRepository.save(entity);
    }

    private Set<TheaterArtistEntity> toTheaterArtistEntitySet(Set<TheaterArtist> artists) {
        if (artists == null || artists.isEmpty()) {
            return Set.of();
        }
        return artists.stream()
                .map(artist -> {
                    Optional<TheaterArtistEntity> existing = theaterArtistRepository.findByArtistCode(artist.getArtistCode());
                    if (existing.isPresent()) {
                        return existing.get();
                    }
                    TheaterArtistEntity entity = new TheaterArtistEntity();
                    entity.fromTheaterArtist(artist);
                    return theaterArtistRepository.save(entity);
                })
                .collect(Collectors.toSet());
    }

    @Override
    public Stream<TheaterPerformance> readAll() {
        return this.theaterPerformanceRepository.findAll().stream()
                .map(TheaterPerformanceEntity::toTheaterPerformance);
    }

    @Override
    public boolean existsByPerformanceCode(String performanceCode) {
        return this.theaterPerformanceRepository.findByPerformanceCode(performanceCode).isPresent();
    }

    @Override
    public Stream<TheaterArtist> findArtistsByPerformanceCode(String performanceCode) {
        return this.theaterPerformanceRepository.findByPerformanceCode(performanceCode)
                .orElseThrow(() -> new NotFoundException("TheaterPerformance performanceCode: " + performanceCode))
                .getPerformanceArtists()
                .stream()
                .map(TheaterArtistEntity::toTheaterArtist);
    }
}
