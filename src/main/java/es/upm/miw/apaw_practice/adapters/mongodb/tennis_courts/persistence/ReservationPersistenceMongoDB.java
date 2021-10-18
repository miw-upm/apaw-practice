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
        if(reservationEntityList.isEmpty()){
            throw new NotFoundException("Reservation: " + ownerName + "/nDate: " + date.toString());
        } else{
          reservationEntityList = reservationEntityList.stream()
                  .filter(entity -> entity.getDate().isEqual(date))
                  .collect(Collectors.toList());
          if(reservationEntityList.size() > 1){
              throw new ConflictException("Hay varias reservas con el ownerName " + ownerName + " y la misma fecha [" + date + "]");
          } else{
              if(reservationEntityList.size() < 1){
                  throw new NotFoundException("Reservation: " + ownerName + "/nDate: " + date);
              }
          }
        }
        return reservationEntityList.get(0);
    }
}
