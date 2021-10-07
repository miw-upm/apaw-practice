package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.domain.services.hotel.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(HotelResource.HOTEL)
public class HotelResource {

    static final String HOTEL = "hotel/hotels";
    static final String ID_ID = "/{id}";
    static final String ROOMS = "/rooms";
    static final String NUMBER_ROOM = "/{numberRoom}";
    static final String ROOM_PRICE = "/price";

    private final HotelService hotelService;

    @Autowired
    public HotelResource(HotelService hotelService) {
        this.hotelService = hotelService;
    }



}
