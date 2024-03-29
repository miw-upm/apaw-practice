package es.upm.miw.apaw_practice.domain.services.formula_one;

import es.upm.miw.apaw_practice.domain.models.formula_one.Race;
import es.upm.miw.apaw_practice.domain.persistence_ports.formula_one.RacePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceService {

    private final RacePersistence racePersistence;

    @Autowired
    public RaceService(RacePersistence racePersistence) {
        this.racePersistence = racePersistence;
    }

    public Race read(String circuitName) {
        return this.racePersistence.readByCircuitName(circuitName);
    }

    public Race updateLaps(String circuitName, Integer laps) {
        Race race = this.racePersistence.readByCircuitName(circuitName);
        race.setLaps(laps);
        return this.racePersistence.update(race);
    }
}
