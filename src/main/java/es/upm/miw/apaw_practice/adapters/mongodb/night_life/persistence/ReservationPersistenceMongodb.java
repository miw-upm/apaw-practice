package es.upm.miw.apaw_practice.adapters.mongodb.night_life.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos.ReservationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities.ReservationEntity;
import es.upm.miw.apaw_practice.domain.models.night_life.Reservation;
import es.upm.miw.apaw_practice.domain.persistence_ports.night_life.ReservationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository("ReservationPersistence")
public class ReservationPersistenceMongodb implements ReservationPersistence {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationPersistenceMongodb(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> updateReservation(BigDecimal price) {
        List<ReservationEntity> reservationEntities = this.reservationRepository.findAll();
        return reservationEntities.stream()
                .map(reservationEntity -> {
                    reservationEntity.setPrice(price);
                    return this.reservationRepository.save(reservationEntity).toReservation();
                })
                .toList();
    }


}
