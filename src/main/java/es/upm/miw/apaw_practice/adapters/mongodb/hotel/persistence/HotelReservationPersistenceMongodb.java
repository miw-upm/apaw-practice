package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelClientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelMainRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelReservationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelClientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelMainEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelReservationEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelRoomEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelClient;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelReservationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository("hotelReservationPersistence")
public class HotelReservationPersistenceMongodb implements HotelReservationPersistence {
    private final HotelReservationRepository hotelReservationRepository;
    private final HotelMainRepository hotelMainRepository;
    private final HotelClientRepository hotelClientRepository;

    @Autowired
    public HotelReservationPersistenceMongodb(HotelReservationRepository hotelReservationRepository, HotelMainRepository hotelMainRepository, HotelClientRepository hotelClientRepository) {
        this.hotelReservationRepository = hotelReservationRepository;
        this.hotelClientRepository = hotelClientRepository;
        this.hotelMainRepository = hotelMainRepository;
    }

    @Override
    public HotelReservation patchReservation(String reservationNumber, HotelReservation reservation) {
        String roomNumber = reservation.getRoomNumber();
        LocalDate reservationDate = reservation.getReservationDate();
        HotelClient client = reservation.getClient();
        HotelReservationEntity reservationEntity = this.hotelReservationRepository
                .findByReservationNumber(reservationNumber)
                .orElseThrow(() -> new NotFoundException(" HotelReservation reservationNumber: " + reservationNumber));
        if (reservation.getRoomNumber() != null) {
            reservationEntity.setRoomNumber(roomNumber);
        }
        if (reservation.getReservationDate() != null) {
            reservationEntity.setReservationDate(reservationDate);
        }
        if (reservation.getClient() != null) {
            HotelClientEntity clientEntity = new HotelClientEntity(client.getIdentityDocument(),
                    client.getName(),
                    client.getPhone(),
                    client.getEmail());
            reservationEntity.setClient(clientEntity);
        }
        return hotelReservationRepository.save(reservationEntity).toReservation();
    }

    public BigDecimal findSumTotalPriceByReservationDate(LocalDate date) {
        List<HotelReservationEntity> reservationsByDate = this.hotelReservationRepository.findAll().stream()
                .filter(res -> res.getReservationDate().equals(date))
                .toList();

        return this.hotelMainRepository.findAll().stream()
                .flatMap(hotel -> {
                    List<String> hotelClientIDs = hotel.getClients().stream()
                            .map(HotelClientEntity::getIdentityDocument)
                            .collect(Collectors.toList());
                    return hotel.getRooms().stream()
                            .filter(room -> room.isReserved() &&
                                    reservationsByDate.stream().anyMatch(reservation ->
                                            reservation.getRoomNumber().equals(room.getNumber()) &&
                                                    hotelClientIDs.contains(reservation.getClient().getIdentityDocument())
                                    )
                            );
                })
                .map(HotelRoomEntity::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
