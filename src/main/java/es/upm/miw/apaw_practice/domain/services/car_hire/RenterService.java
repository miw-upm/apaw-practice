package es.upm.miw.apaw_practice.domain.services.car_hire;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.car_hire.Renter;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_hire.RenterPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RenterService {

    private final RenterPersistence renterPersistence;

    @Autowired
    public RenterService(RenterPersistence renterPersistence) {
        this.renterPersistence = renterPersistence;
    }

    public Renter create(Renter renter) {
        this.assertDniNotExist(renter.getDni());
        return this.renterPersistence.create(renter);
    }

    public void assertDniNotExist(String dni) {
        if (this.renterPersistence.existDni(dni)) {
            throw new ConflictException("DNI already exists: " + dni);
        }
    }

    public Renter updateLikedCar(String dni, Renter renter) {
        Renter renterToUpdate = this.renterPersistence.readByDni(dni);
        return this.renterPersistence.update(renterToUpdate.getDni(), renter);
    }
}
