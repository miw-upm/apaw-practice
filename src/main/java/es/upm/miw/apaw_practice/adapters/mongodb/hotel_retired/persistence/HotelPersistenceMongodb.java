package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos.HotelRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos.RoomRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.BookingEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.GuestEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.HotelEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.RoomEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Hotel;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel_retired.HotelPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("hotelPersistence")
public class HotelPersistenceMongodb implements HotelPersistence {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public HotelPersistenceMongodb(
            HotelRepository hotelRepository,
            RoomRepository roomRepository
    ) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public Stream<Hotel> readAll() {
        return this.hotelRepository
                .findAll().stream()
                .map(HotelEntity::toHotel);
    }

    @Override
    public Hotel create(Hotel hotel) {
        List<RoomEntity> roomEntities = hotel.getRooms().stream()
                .map(room -> new RoomEntity(
                        room.getNum(),
                        room.getOccupied(),
                        room.getNumBeds(),
                        room.getPrice(),
                        room.getBookings().stream()
                                .map(booking -> new BookingEntity(
                                        booking.getConfirmed(),
                                        booking.getDateIn(),
                                        booking.getDateOut(),
                                        new GuestEntity(
                                                booking.getGuest().getNif(),
                                                booking.getGuest().getFullName(),
                                                booking.getGuest().getBirthDay())))
                                .toList()
                ))
                .toList();
        return this.hotelRepository
                .save(new HotelEntity(hotel.getCif(), hotel.getHotelName(), hotel.getAddress(), roomEntities))
                .toHotel();
    }

    @Override
    public Hotel update(String cif, Hotel hotel) {
        HotelEntity hotelEntity = this.hotelRepository
                .findByCif(cif)
                .orElseThrow(() -> new NotFoundException("Hotel cif: " + cif));
        hotelEntity.fromHotel(hotel);
        return this.hotelRepository
                .save(hotelEntity)
                .toHotel();
    }

    @Override
    public Hotel read(String cif) {
        return this.hotelRepository
                .findByCif(cif)
                .orElseThrow(() -> new NotFoundException("Hotel CIF: " + cif))
                .toHotel();
    }

    @Override
    public boolean existCIF(String cif) {
        return this.hotelRepository
                .findByCif(cif)
                .isPresent();
    }

    @Override
    public void delete(String cif) {
        this.hotelRepository.deleteByCif(cif);
    }

    @Override
    public BigDecimal findTotalSumOfPrice(String hotelName, String fullName) {
        return this.hotelRepository.findAll().stream()
                .filter(hotelEntity -> hotelName.equals(hotelEntity.getHotelName()))
                .flatMap(hotelEntity -> hotelEntity.getRoomsEntities().stream()
                        .filter(roomEntity -> roomEntity.getBookingEntities().stream().anyMatch(bookingEntity -> fullName.equals(bookingEntity.getGuestEntity().getFullName()))))
                .map(RoomEntity::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Stream<String> findNonDuplicatedHotelNamesByNumBedsAndNumBookings(int numBeds, int numBookings) {
        return this.hotelRepository.findAll().stream()
                .filter(hotelEntity -> hotelEntity.getRoomsEntities().stream()
                        .filter(roomEntity -> roomEntity.getNumBeds() > numBeds)
                        .flatMap(roomEntity -> roomEntity.getBookingEntities().stream())
                        .filter(BookingEntity::isConfirmed)
                        .count() > numBookings)
                .map(HotelEntity::getHotelName)
                .distinct();
    }
}
