package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;


import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelClientRepository;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelReservationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelClientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelReservationEntity;
import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelClient;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelClientPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository("hotelClientPersistence")
public class HotelClientPersistenceMongodb implements HotelClientPersistence {

    private final HotelClientRepository hotelClientRepository;
    private final HotelReservationRepository hotelReservationRepository;

    @Autowired
    public HotelClientPersistenceMongodb(HotelClientRepository hotelClientRepository,
                                         HotelReservationRepository hotelReservationRepository) {
        this.hotelClientRepository = hotelClientRepository;
        this.hotelReservationRepository = hotelReservationRepository;
    }

    @Override
    public HotelClient create(HotelClient client) {
        String dni = client.getIdentityDocument();
        boolean existDNI = this.existDNI(dni);
        String rNumber = client.getReservation().getReservationNumber();
        HotelReservation reservation = this.getReservation(rNumber);
        String reservationNumber = reservation.getReservationNumber();
        String roomNumber = reservation.getRoomNumber();
        LocalDate reservationDate = reservation.getReservationDate();
        HotelReservationEntity reservationEntity = new HotelReservationEntity(reservationNumber, roomNumber, reservationDate);
        if (existDNI) {
            throw new ConflictException("DNI exist: " + dni);
        } else {
            return this.hotelClientRepository
                    .save(new HotelClientEntity(client.getIdentityDocument(), client.getName(), client.getPhone(), client.getEmail(), reservationEntity))
                    .toClient();
        }
    }

    @Override
    public boolean existDNI(String dni) {
        return this.hotelClientRepository
                .findByIdentityDocument(dni)
                .isPresent();
    }

    @Override
    public HotelReservation getReservation(String rNumber) {
        return this.hotelReservationRepository
            .findByReservationNumber(rNumber)
            .orElseThrow(() -> new NotFoundException("HotelReservationNumber: " + rNumber))
            .toReservation();}
}