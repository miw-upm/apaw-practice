package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelClientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelReservationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelClientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelReservationEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelClient;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelReservationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository("hotelReservationPersistence")
public class HotelReservationPersistenceMongodb implements HotelReservationPersistence {
    private final HotelReservationRepository hotelReservationRepository;

    @Autowired
    public HotelReservationPersistenceMongodb(HotelReservationRepository hotelReservationRepository, HotelClientRepository hotelClientRepository) {
        this.hotelReservationRepository = hotelReservationRepository;
    }

    @Override
    public HotelReservation patchReservation(String reservationNumber, String roomNumber, LocalDate reservationDate, HotelClient client){
        HotelReservationEntity reservationEntity = this.hotelReservationRepository
                .findByReservationNumber(reservationNumber)
                .orElseThrow(() -> new NotFoundException(" HotelReservation reservationNumber: " + reservationNumber));
        if(roomNumber != null ) {
            reservationEntity.setRoomNumber(roomNumber);
        }
        if(reservationDate != null) {
            reservationEntity.setReservationDate(reservationDate);
        }
        if(client != null) {
            HotelClientEntity clientEntity = new HotelClientEntity(client.getIdentityDocument(),
                    client.getName(),
                    client.getPhone(),
                    client.getEmail());
            reservationEntity.setClient(clientEntity);
        }
        return hotelReservationRepository.save(reservationEntity).toReservation();
    }
}
