package es.upm.miw.apaw_practice.domain.services.night_life;
import es.upm.miw.apaw_practice.domain.models.night_life.Reservation;
import es.upm.miw.apaw_practice.domain.persistence_ports.night_life.ReservationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationPersistence reservationPersistence;

    @Autowired
    public ReservationService(ReservationPersistence reservationPersistence) {
        this.reservationPersistence = reservationPersistence;
    }
    public List<Reservation> updateReservation(BigDecimal price) {
        return this.reservationPersistence.updateReservation(price);
    }
}
