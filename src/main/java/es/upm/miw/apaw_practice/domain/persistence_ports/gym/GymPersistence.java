package es.upm.miw.apaw_practice.domain.persistence_ports.gym;

import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import es.upm.miw.apaw_practice.domain.models.gym.Gym;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GymPersistence {

    Gym readByAddress(String address);

    Gym update(String address, Gym gym);

    List<Athlete> findAthleteByGymLabel(String label);
}
