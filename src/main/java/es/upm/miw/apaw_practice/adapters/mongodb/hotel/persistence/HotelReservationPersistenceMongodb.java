package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelReservationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelReservationEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelReservationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository("hotelReservationPersistence")
public class HotelReservationPersistenceMongodb implements HotelReservationPersistence {
    private final HotelReservationRepository hotelReservationRepository;

    @Autowired
    public HotelReservationPersistenceMongodb(HotelReservationRepository hotelReservationRepository) {
        this.hotelReservationRepository = hotelReservationRepository;
    }

    @Override
    public HotelReservation patchReservation(String reservationNumber, String roomNumber, LocalDate reservationDate){
        HotelReservation reservation = this.hotelReservationRepository
                .findByReservationNumber(reservationNumber)
                .orElseThrow(() -> new NotFoundException(" HotelReservation re: reservationNumber" + reservationNumber))
                .toReservation();
        if(roomNumber != null ) {
            reservation.setRoomNumber(roomNumber);
        }
        if(reservationDate != null) {
            reservation.setReservationDate(reservationDate);
        }
        HotelReservationEntity reservationEntity = reservation.toReservationEntity();
        return hotelReservationRepository.save(reservationEntity).toReservation();
    }
}
