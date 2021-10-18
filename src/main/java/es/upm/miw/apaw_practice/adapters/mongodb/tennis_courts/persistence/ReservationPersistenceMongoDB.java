package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.daos.ReservationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities.ReservationEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Reservation;
import es.upm.miw.apaw_practice.domain.persistence_ports.tennis_courts.ReservationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("reservationPersistence")
public class ReservationPersistenceMongoDB implements ReservationPersistence {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationPersistenceMongoDB(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation read(String ownerName){
        return this.findByOwnerName(ownerName).toReservation();
    }

    @Override
    public void delete(String ownerName) {
        this.reservationRepository.delete(findByOwnerName(ownerName));
    }

    private ReservationEntity findByOwnerName(String ownerName){
        return this.reservationRepository.findByOwnerName(ownerName)
                .orElseThrow(() -> new NotFoundException("Reservation: " + ownerName));
    }
}
