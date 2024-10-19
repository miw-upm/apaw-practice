package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;


import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelClientRepository;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelClientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelReservationEntity;
import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelClient;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelClientPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository("hotelClientPersistence")
public class HotelClientPersistenceMongodb implements HotelClientPersistence {

    private final HotelClientRepository hotelClientRepository;

    @Autowired
    public HotelClientPersistenceMongodb(HotelClientRepository hotelClientRepository) {
        this.hotelClientRepository = hotelClientRepository;
    }

    @Override
    public HotelClient create(HotelClient client) {
        String dni = client.getIdentityDocument();
        boolean exist = this.existDNI(dni);
        if(exist){
            throw new ConflictException("DNI exist: " + dni);
        }
        else {
            String reservationNumber = client.getReservation().getReservationNumber();
            String roomNumber = client.getReservation().getRoomNumber();
            LocalDate reservationDate = client.getReservation().getReservationDate();
            HotelReservationEntity reservation = new HotelReservationEntity(reservationNumber, roomNumber, reservationDate);
            return this.hotelClientRepository
                    .save(new HotelClientEntity(client.getIdentityDocument(), client.getName(), client.getPhone(), client.getEmail(), reservation))
                    .toClient();
        }
    }

    @Override
    public boolean existDNI(String dni) {
        return this.hotelClientRepository
                .findByIdentityDocument(dni)
                .isPresent();
    }

}