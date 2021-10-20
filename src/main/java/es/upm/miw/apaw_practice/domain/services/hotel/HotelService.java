package es.upm.miw.apaw_practice.domain.services.hotel;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelGuestRepository;
import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class HotelService {
    private final HotelPersistence hotelPersistence;
    private final HotelGuestRepository hotelGuestRepository;

    @Autowired
    public HotelService(HotelPersistence hotelPersistence, HotelGuestRepository hotelGuestRepository) {
        this.hotelPersistence = hotelPersistence;
        this.hotelGuestRepository = hotelGuestRepository;
    }

    public void updateRoomPrice(String id, Integer numberRoom, BigDecimal price) {
        this.hotelPersistence.updateRoomPrice(id, numberRoom, price);
    }

    public Hotel read(String id) {
        return this.hotelPersistence.read(id);
    }

    public void update(String id, Hotel hotel) {
        if (hotel.getDirection() == null || hotel.getNumberStars() == null) {
            throw new BadRequestException("For this update, invalid inputs");
        } else {
            this.hotelPersistence.update(id, hotel);
        }

    }

    public List<Hotel> findHotelNameListByGuestName(String name) {
        if (this.hotelGuestRepository.findByName(name).isEmpty()) {
            throw new NotFoundException("Name : " + name + " not found");
        } else {
            return this.hotelPersistence.findHotelNameListByGuestName(name);
        }

    }
}
