package es.upm.miw.apaw.adapters.mongodb.winery.persistence;

import es.upm.miw.apaw.adapters.mongodb.winery.daos.ReservationRepository;
import es.upm.miw.apaw.adapters.mongodb.winery.daos.TastingSessionRepository;
import es.upm.miw.apaw.adapters.mongodb.winery.entities.ReservationEntity;
import es.upm.miw.apaw.adapters.mongodb.winery.entities.TastingSessionEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.winery.Reservation;
import es.upm.miw.apaw.domain.persistenceports.winery.ReservationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("reservationPersistence")
public class ReservationPersistenceMongodb implements ReservationPersistence {

    private final ReservationRepository reservationRepository;
    private final TastingSessionRepository tastingSessionRepository;

    @Autowired
    public ReservationPersistenceMongodb(ReservationRepository reservationRepository, TastingSessionRepository tastingSessionRepository) {
        this.reservationRepository = reservationRepository;
        this.tastingSessionRepository = tastingSessionRepository;
    }

    @Override
    public Reservation create(Reservation reservation) {
        ReservationEntity reservationEntity = new ReservationEntity(reservation);
        TastingSessionEntity tastingSessionEntity = this.tastingSessionRepository.findById(reservation.getTastingSession().getId())
                .orElseThrow(() -> new NotFoundException("TastingSession id: " + reservation.getTastingSession().getId()));
        reservationEntity.setTastingSessionEntity(tastingSessionEntity);

        return this.reservationRepository.save(reservationEntity).toReservation();
    }

}
