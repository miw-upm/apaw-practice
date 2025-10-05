package es.upm.miw.apaw.adapters.mongodb.sports.academy.persistence;

import es.upm.miw.apaw.adapters.mongodb.sports.academy.daos.ISportModalityRepository;
import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.SportModalityEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.sports.academy.SportModality;
import es.upm.miw.apaw.domain.persistenceports.sports.academy.ISportModalityPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository("sportModalityPersistence")
public class SportModalityPersistenceMongodb implements ISportModalityPersistence {

    private final ISportModalityRepository sportModalityRepository;

    @Autowired
    public SportModalityPersistenceMongodb(ISportModalityRepository sportModalityRepository) {
        this.sportModalityRepository = sportModalityRepository;
    }

    @Override
    public Stream<SportModality> getAll(int page, int pageSize) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return this.sportModalityRepository
                .findAll(pageable)
                .stream()
                .map(SportModalityEntity::toSportModality);
    }

    @Override
    public SportModality create(SportModality sportModality) {
        return this.sportModalityRepository
                .save(new SportModalityEntity(sportModality))
                .toSportModality();
    }

    @Override
    public SportModality update(UUID id, SportModality sportModality) {
        SportModalityEntity sportModalityEntity = this.sportModalityRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Sport Modality id: " + id));
        sportModalityEntity.fromSportModality(sportModality);
        return this.sportModalityRepository
                .save(sportModalityEntity)
                .toSportModality();
    }

    @Override
    public SportModality getById(UUID id) {
        return this.sportModalityRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Sport Modality id: " + id))
                .toSportModality();
    }
}
