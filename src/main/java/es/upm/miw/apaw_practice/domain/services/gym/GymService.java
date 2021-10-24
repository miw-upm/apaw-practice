package es.upm.miw.apaw_practice.domain.services.gym;

import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import es.upm.miw.apaw_practice.domain.models.gym.Gym;
import es.upm.miw.apaw_practice.domain.persistence_ports.gym.GymPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymService {
    private final GymPersistence gymPersistence;

    @Autowired
    public GymService(GymPersistence gymPersistence) {
        this.gymPersistence = gymPersistence;
    }


    public Gym updateCellphone(String address, Gym gym) {
        Gym gymToUpdate = this.gymPersistence.readByAddress(address);

        return this.gymPersistence.update(gymToUpdate.getAddress(), gym);
    }

    public List<Athlete> findAthleteByGymLabel(String label) {

        return this.gymPersistence.findAthleteByGymLabel(label);
    }
}
