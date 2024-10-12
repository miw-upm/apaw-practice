package es.upm.miw.apaw_practice.adapters.rest.hotel_retired;

import es.upm.miw.apaw_practice.domain.models.hotel_retired.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import es.upm.miw.apaw_practice.domain.services.hotel_retired.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(HotelResource.HOTELS)
public class HotelResource {

    static final String HOTELS = "/hotel-retired/hotels";

    static final String CIF_ID = "/{cif}";
    static final String ROOMS = "/rooms";
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

    @GetMapping(CIF_ID)
    public Hotel read(@PathVariable String cif) {
        return this.hotelService.read(cif);
    }

    @DeleteMapping(CIF_ID)
    public void delete(@PathVariable String cif) {
        this.hotelService.delete(cif);
    }

    @PutMapping(CIF_ID)
    public Hotel update(@PathVariable String cif, @RequestBody Hotel hotel) {
        return this.hotelService.update(cif, hotel);
    }
}
