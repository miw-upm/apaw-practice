package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.RenterRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.RenterEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_hire.Renter;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_hire.RenterPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("renterPersistence")
public class RenterPersistenceMongodb implements RenterPersistence {

    private final RenterRepository renterRepository;

    @Autowired
    public RenterPersistenceMongodb(RenterRepository renterRepository) {
        this.renterRepository = renterRepository;
    }

    @Override
    public Renter create(Renter renter) {
        return this.renterRepository
                .save(new RenterEntity(renter))
                .toRenter();
    }

    @Override
    public boolean existDni(String dni) {
        return this.renterRepository.findByDni(dni).isPresent();
    }

    @Override
    public Renter readByDni(String dni) {
        return this.renterRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Renter with DNI: " + dni))
                .toRenter();
    }

    @Override
    public Renter update(String dni, Renter renter) {
        RenterEntity renterToUpdate = this.renterRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Renter with DNI: " + dni));
        renterToUpdate.setLikedCar(renter.getLikedCar());
        return this.renterRepository.save(renterToUpdate).toRenter();
    }
}
