package es.upm.miw.apaw_practice.domain.services.gym;

import es.upm.miw.apaw_practice.domain.persistence_ports.gym.CoachPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoachService {

    private final CoachPersistence coachPersistence;

    @Autowired
    public CoachService(CoachPersistence coachPersistence) {
        this.coachPersistence = coachPersistence;
    }

    public void delete(String dni) {
        this.coachPersistence.delete(dni);
    }
}
