package es.upm.miw.apaw_practice.domain.persistence_ports.car_hire;

import es.upm.miw.apaw_practice.domain.models.car_hire.Renter;
import org.springframework.stereotype.Repository;

@Repository
public interface RenterPersistence {

    Renter create(Renter renter);

    Renter readByDni(String dni);

    Renter update(String dni, Renter renter);

    boolean existDni(String dni);
}
