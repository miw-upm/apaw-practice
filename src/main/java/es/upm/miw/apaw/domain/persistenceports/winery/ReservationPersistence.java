package es.upm.miw.apaw.domain.persistenceports.winery;

import es.upm.miw.apaw.domain.models.winery.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationPersistence {

    Reservation create(Reservation reservation);

}
