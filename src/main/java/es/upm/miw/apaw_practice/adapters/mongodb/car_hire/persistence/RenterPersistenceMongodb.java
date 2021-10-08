package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.RenterRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.RenterEntity;
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
}
