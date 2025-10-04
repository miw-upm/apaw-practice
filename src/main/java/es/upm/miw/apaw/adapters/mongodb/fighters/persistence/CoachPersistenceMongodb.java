package es.upm.miw.apaw.adapters.mongodb.fighters.persistence;

import es.upm.miw.apaw.adapters.mongodb.fighters.daos.CoachRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.fighters.Coach;
import es.upm.miw.apaw.domain.persistenceports.fighters.CoachPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("coachPersistence")
public class CoachPersistenceMongodb implements CoachPersistence {
    private final CoachRepository coachRepository;

    @Autowired
    public CoachPersistenceMongodb(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @Override
    public Coach readByFullName(String fullName) {
        return this.coachRepository.findByFullName(fullName)
                .orElseThrow(() -> new NotFoundException(" FullName: " + fullName))
                .toCoach();
    }
}
