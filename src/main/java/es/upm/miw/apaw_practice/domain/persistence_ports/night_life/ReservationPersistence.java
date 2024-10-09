package es.upm.miw.apaw_practice.domain.persistence_ports.night_life;
import es.upm.miw.apaw_practice.domain.models.night_life.Reservation;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ReservationPersistence {
    List<Reservation> updateReservation(BigDecimal price);

}
