package es.upm.miw.apaw_practice.domain.services.hotel_retired;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel_retired.HotelPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@Service
public class HotelService {

    private final HotelPersistence hotelPersistence;

    @Autowired
    public HotelService(HotelPersistence hotelPersistence) {
        this.hotelPersistence = hotelPersistence;
    }

    public Hotel create(Hotel hotel) {
        this.assertCifNotExists(hotel.getCif());
        return this.hotelPersistence.create(hotel);
    }

    private void assertCifNotExists(String cif) {
        if (this.hotelPersistence.existCIF(cif)) {
            throw new ConflictException("CIF exists: " + cif);
        }
    }

    public Hotel read(String cif) {
        return this.hotelPersistence.read(cif);
    }

    public Hotel update(String cif, Hotel hotel) {
        return this.hotelPersistence.update(cif, hotel);
    }

    public void delete(String cif) {
        this.hotelPersistence.delete(cif);
    }

    public Hotel updateRooms(String cif, List<Room> rooms) {
        Hotel hotel = this.hotelPersistence.read(cif);
        hotel.setRooms(rooms);
        return this.hotelPersistence.update(cif, hotel);
    }

    public BigDecimal findTotalSumOfPrice(String hotelName, String fullName) {
        return this.hotelPersistence.findTotalSumOfPrice(hotelName, fullName);
    }

    public Stream<String> findNonDuplicatedHotelNamesByNumBedsAndNumBookings(int numBeds, int numBookings) {
        return this.hotelPersistence.findNonDuplicatedHotelNamesByNumBedsAndNumBookings(numBeds, numBookings);
    }
}
