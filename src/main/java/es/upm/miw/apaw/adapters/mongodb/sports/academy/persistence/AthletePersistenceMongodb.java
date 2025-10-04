package es.upm.miw.apaw.adapters.mongodb.sports.academy.persistence;

import es.upm.miw.apaw.adapters.mongodb.sports.academy.daos.IAthleteRepository;
import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.AthleteEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.sports.academy.Athlete;
import es.upm.miw.apaw.domain.persistenceports.sports.academy.IAthletePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;
import java.util.stream.Stream;

@Repository("athletePersistence")
public class AthletePersistenceMongodb implements IAthletePersistence {

    private final IAthleteRepository athleteRepository;

    @Autowired
    public AthletePersistenceMongodb(IAthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
    }

    @Override
    public Stream<Athlete> getAll(int page, int pageSize) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return this.athleteRepository
                .findAll(pageable)
                .stream()
                .map(AthleteEntity::toAthlete);
    }

    @Override
    public Athlete create(Athlete athlete) {
        return this.athleteRepository
                .save(new AthleteEntity(athlete))
                .toAthlete();
    }

    @Override
    public Athlete update(UUID id, Athlete athlete) {
        AthleteEntity athleteEntity = this.athleteRepository
                .findByUserDtoId(athlete.getUser().getId())
                .orElseThrow(() -> new NotFoundException("Athlete user dto id: " + id));
        athleteEntity.fromAthlete(athlete);
        return this.athleteRepository
                .save(athleteEntity)
                .toAthlete();
    }

    @Override
    public Athlete getById(UUID id) {
        return this.athleteRepository
                .findByUserDtoId(id)
                .orElseThrow(() -> new NotFoundException("Athlete user dto id: " + id))
                .toAthlete();
    }
}
