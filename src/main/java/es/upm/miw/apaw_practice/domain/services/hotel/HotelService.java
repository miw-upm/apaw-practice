package es.upm.miw.apaw_practice.domain.services.hotel;

import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class HotelService {
    private final HotelPersistence hotelPersistence;

    @Autowired
    public HotelService(HotelPersistence hotelPersistence) {
        this.hotelPersistence = hotelPersistence;
    }

    public void updateRoomPrice(String id, Integer numberRoom, BigDecimal price) {
        this.hotelPersistence.updateRoomPrice(id, numberRoom, price);
    }

    public Hotel read(String id) {
        return this.hotelPersistence.read(id);
    }

    public void update(String id, Hotel hotel) {
        if(hotel.getDirection() == null || hotel.getNumberStars() == null){
            throw new BadRequestException("For this update, invalid inputs");
        }else{
            this.hotelPersistence.update(id, hotel);
        }


    }
}
