package es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.daos.GymRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.gym.Gym;
import es.upm.miw.apaw_practice.domain.persistence_ports.gym.GymPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("GymPersistence")
public class GymPresistenceMongodb implements GymPersistence {
    private final GymRepository gymRepository;

    @Autowired
    public GymPresistenceMongodb(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }


    @Override
    public Gym findByLabel(String label) {
        return this.gymRepository.findByLabel(label).
                orElseThrow(() -> new NotFoundException("Gym with name :" + label)).ToGym();

    }
}