package es.upm.miw.apaw_practice.domain.persistence_ports.gym;

import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import es.upm.miw.apaw_practice.domain.models.gym.AthleteNameUpdating;
import org.springframework.stereotype.Repository;

@Repository

public interface AthletePersistence {
    Athlete creat(Athlete athlete);

    boolean existNie(String nie);

    void updateNextFumigation(AthleteNameUpdating athleteNameUpdating);

}
