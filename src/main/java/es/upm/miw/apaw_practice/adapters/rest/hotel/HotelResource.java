package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel.Room;
import es.upm.miw.apaw_practice.domain.services.hotel.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(HotelResource.HOTELS)
public class HotelResource {
    static final String HOTELS = "/hotel/hotels";
    static final String ID_ID = "/{id}";
    static final String PRICE_ROOM = "/rooms/{number}/price";
    static final String HOTELS_HOTELGUEST_NAME = "/name/rooms/hotelguests/{name}";

    private final HotelService hotelService;

    @Autowired
    public HotelResource(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping(HotelResource.ID_ID)
    public Hotel read(@PathVariable String id) {
        return this.hotelService.read(id);
    }

    @PutMapping(HotelResource.ID_ID + HotelResource.PRICE_ROOM)
    public void updateRoomPrice(@PathVariable String id, @PathVariable Integer number, @RequestBody Room room) {
        this.hotelService.updateRoomPrice(id, number, room.getPrice());
    }

    @PatchMapping(HotelResource.ID_ID)
    public void update(@PathVariable String id, @RequestBody Hotel hotel) {
        this.hotelService.update(id, hotel);
    }

    @GetMapping(HotelResource.HOTELS_HOTELGUEST_NAME)
    public List<Hotel> findHotelNameListByGuestName(@PathVariable String name) {
        return this.hotelService.findHotelNameListByGuestName(name);
    }

}
