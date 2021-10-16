package es.upm.miw.apaw_practice.domain.services.gym;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import es.upm.miw.apaw_practice.domain.persistence_ports.gym.AthletePersistence;
import org.springframework.stereotype.Service;

@Service
public class AthleteService {

    private final AthletePersistence athletePersistence;

    public AthleteService(AthletePersistence athletePersistence) {
        this.athletePersistence = athletePersistence;
    }

    public Athlete create(Athlete athlete) {
        this.assertNieNotExist(athlete.getNie());
        return this.athletePersistence.creat(athlete);
    }

    public void assertNieNotExist(String nie) {
        if (this.athletePersistence.existNie(nie)) {
            throw new ConflictException("Nie exist: " + nie);
        }
    }
}
