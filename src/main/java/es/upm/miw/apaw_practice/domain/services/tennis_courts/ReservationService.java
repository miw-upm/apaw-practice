package es.upm.miw.apaw_practice.domain.services.tennis_courts;

import es.upm.miw.apaw_practice.domain.persistence_ports.tennis_courts.ReservationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservationService {
    private final ReservationPersistence reservationPersistence;

    @Autowired
    public ReservationService(ReservationPersistence reservationPersistence){
        this.reservationPersistence = reservationPersistence;
    }

    public void delete(String ownerName, LocalDateTime date){
        this.reservationPersistence.delete(ownerName, date);
    }
}
