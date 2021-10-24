package es.upm.miw.apaw_practice.adapters.mongodb.gym.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.gym.daos.GymRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.GymEntity;
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
    public Gym readByAddress(String address) {
        return this.gymRepository
                .findByAddress(address)
                .orElseThrow(() -> new NotFoundException("Gym Address: " + address)).ToGym();

    }

    @Override
    public Gym update(String address, Gym gym) {
        GymEntity addressUpdate = this.gymRepository.findByAddress(address)
                .orElseThrow(() -> new NotFoundException("gym Address: " + address));
        addressUpdate.setCellphone(gym.getCellphone());
        return this.gymRepository.save(addressUpdate).ToGym();

    }
}
