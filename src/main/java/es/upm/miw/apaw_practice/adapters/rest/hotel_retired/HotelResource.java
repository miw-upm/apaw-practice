package es.upm.miw.apaw_practice.adapters.rest.hotel_retired;

import es.upm.miw.apaw_practice.domain.models.hotel_retired.Hotel;
import es.upm.miw.apaw_practice.domain.services.hotel_retired.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(HotelResource.HOTELS)
public class HotelResource {

    static final String HOTELS = "/hotel-retired/hotels";

    static final String SEARCH = "/search";

    private final HotelService hotelService;

    @Autowired
    public HotelResource(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public Hotel create(@RequestBody Hotel hotel) {
        return this.hotelService.create(hotel);
    }
}
