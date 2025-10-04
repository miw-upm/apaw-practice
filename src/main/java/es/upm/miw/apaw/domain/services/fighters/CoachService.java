package es.upm.miw.apaw.domain.services.fighters;

import es.upm.miw.apaw.domain.models.fighters.Coach;
import es.upm.miw.apaw.domain.persistenceports.fighters.CoachPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoachService {
    private final CoachPersistence coachPersistence;

    @Autowired
    public CoachService(CoachPersistence coachPersistence) {
        this.coachPersistence = coachPersistence;
    }

    public Coach readByFullName(String fullName) {
        return coachPersistence.readByFullName(fullName);
    }
}
