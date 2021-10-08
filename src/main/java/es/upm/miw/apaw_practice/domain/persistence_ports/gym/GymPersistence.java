package es.upm.miw.apaw_practice.domain.persistence_ports.gym;

import es.upm.miw.apaw_practice.domain.models.gym.Gym;
import org.springframework.stereotype.Repository;

@Repository
public interface GymPersistence {
    Gym findByLabel(String label);
}
