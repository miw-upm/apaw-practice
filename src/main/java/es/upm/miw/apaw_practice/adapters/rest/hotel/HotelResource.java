package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel.Room;
import es.upm.miw.apaw_practice.domain.services.hotel.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(HotelResource.HOTELS)
public class HotelResource {
    static final String HOTELS = "/hotel/hotels";
    static final String ID_ID = "/{id}";
    static final String ROOMS = "/rooms";
    static final String NUMBER_ROOM = "/{number}";
    static final String PRICE_ROOM = "/price";

    private final HotelService hotelService;

    @Autowired
    public HotelResource(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping(HotelResource.ID_ID)
    public Hotel read(@PathVariable  String id){
       return this.hotelService.read(id);
    }

    @PutMapping(HotelResource.ID_ID + HotelResource.ROOMS + HotelResource.NUMBER_ROOM + HotelResource.PRICE_ROOM)
    public void updateRoomPrice(@PathVariable String id, @PathVariable Integer number, @RequestBody Room room){
        this.hotelService.updateRoomPrice(id, number, room.getPrice());
    }
}
