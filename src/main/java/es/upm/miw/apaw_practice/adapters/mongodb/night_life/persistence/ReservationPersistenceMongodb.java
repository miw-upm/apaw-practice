package es.upm.miw.apaw_practice.adapters.mongodb.night_life.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos.ReservationRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.night_life.ReservationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ReservationPersistence")
public class ReservationPersistenceMongodb implements ReservationPersistence {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationPersistenceMongodb(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
}
