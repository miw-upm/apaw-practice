package es.upm.miw.apaw_practice.domain.persistence_ports.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface HotelPersistence {

    void updateRoomPrice(String id, Integer numberRoom, BigDecimal price);

    Hotel read(String id);

    void update(String id, Hotel hotel);

    List<Hotel> findHotelNameListByGuestName(String name);

}
