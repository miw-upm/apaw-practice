package es.upm.miw.apaw.domain.persistenceports.winery;

import es.upm.miw.apaw.domain.models.winery.Reservation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationPersistence {

    Reservation create(Reservation reservation);

    List<UUID> findReservationIdsByWineName(String name);
}
