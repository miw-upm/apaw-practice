package es.upm.miw.apaw.domain.services.sports.academy;

import es.upm.miw.apaw.domain.models.sports.academy.SportModality;
import es.upm.miw.apaw.domain.models.sports.academy.dtos.UpdateSportMobilityActivation;
import es.upm.miw.apaw.domain.persistenceports.sports.academy.ISportModalityPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SportModalityService {

    private final ISportModalityPersistence sportModalityPersistence;

    @Autowired
    public SportModalityService(ISportModalityPersistence sportModalityPersistence) {
        this.sportModalityPersistence = sportModalityPersistence;
    }

    public void updateActivation(UUID id, UpdateSportMobilityActivation updateSportMobilityActivation) {
        var sportMobility = this.sportModalityPersistence.getById(id);
        sportMobility.setActive(updateSportMobilityActivation.isActive());
        this.sportModalityPersistence.update(id, sportMobility);
    }

    public SportModality getById(UUID id) {
        return this.sportModalityPersistence.getById(id);
    }

    public  void delete(UUID id) {
        this.sportModalityPersistence.delete(id);
    }
}
