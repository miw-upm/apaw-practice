package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.daos.ReservationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities.ReservationEntity;
import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Reservation;
import es.upm.miw.apaw_practice.domain.persistence_ports.tennis_courts.ReservationPersistence;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Repository("reservationPersistence")
public class ReservationPersistenceMongoDB implements ReservationPersistence {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationPersistenceMongoDB(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation read(String ownerName, LocalDateTime date){
        return this.findByOwnerName(ownerName, date).toReservation();
    }

    @Override
    public void delete(String ownerName, LocalDateTime date) {
        this.reservationRepository.delete(findByOwnerName(ownerName, date));
    }

    private ReservationEntity findByOwnerName(String ownerName, LocalDateTime date){
        List<ReservationEntity> reservationEntityList = this.reservationRepository.findByOwnerName(ownerName);
        checkResultError(reservationEntityList, false);
        reservationEntityList = reservationEntityList.stream()
                .filter(entity -> entity.getDate().isEqual(date))
                .collect(Collectors.toList());
        checkResultError(reservationEntityList, true);
        return reservationEntityList.get(0);
    }

    private void checkResultError(List<ReservationEntity> reservationEntityList, boolean unique){
        if(reservationEntityList.isEmpty()){
            throw new NotFoundException("No se ha encontrado la reserva buscada");
        }
        if(unique && reservationEntityList.size() > 1){
            throw new ConflictException("Existen dos reservas con el mismo dueño y fecha");
        }

    }
}
