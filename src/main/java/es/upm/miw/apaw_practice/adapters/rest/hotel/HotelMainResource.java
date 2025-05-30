package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelMain;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelRoom;
import es.upm.miw.apaw_practice.domain.services.hotel.HotelMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(HotelMainResource.HOTELS)
public class HotelMainResource {

    static final String HOTELS = "/hotel/hotels";
    static final String NAMES = "/{name}";
    static final String ROOMS = "/rooms";
    static final String NUMBERS = "/{number}";
    static final String SEARCH = "/search";
    private final HotelMainService hotelMainService;

    @Autowired
    public HotelMainResource(HotelMainService hotelMainService) {
        this.hotelMainService = hotelMainService;
    }

    @GetMapping(NAMES)
    public HotelMain findByName(@PathVariable String name) {
        return this.hotelMainService.findByName(name);
    }

    @DeleteMapping(NAMES)
    public void delete(@PathVariable String name) {
        this.hotelMainService.delete(name);
    }

    @PutMapping(NAMES + ROOMS + NUMBERS)
    public HotelMain updateRoom(@PathVariable String name, @PathVariable String number, @RequestBody HotelRoom room) {
        return this.hotelMainService.updateRoom(name, number, room);
    }

    @GetMapping(SEARCH)
    public Stream<String> findNonRepeatedRoomNumberByType(@RequestParam String q){
        String type = new LexicalAnalyzer().extractWithAssure(q, "type");
        return this.hotelMainService.findNonRepeatedRoomNumberByType(type);
    }
}
