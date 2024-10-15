package es.upm.miw.apaw_practice.adapters.rest.hotel_retired;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import es.upm.miw.apaw_practice.domain.services.hotel_retired.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

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

    @PatchMapping(CIF_ID + ROOMS)
    public Hotel updateRooms(@PathVariable String cif, @RequestBody List<Room> rooms) {
        return this.hotelService.updateRooms(cif, rooms);
    }

    @GetMapping(SEARCH)
    public BigDecimal findTotalSumOfPrice(@RequestParam String q) {
        String hotelName = new LexicalAnalyzer().extractWithAssure(q, "hotelName").trim();
        String fullName = new LexicalAnalyzer().extractWithAssure(q, "fullName").trim();
        return this.hotelService.findTotalSumOfPrice(hotelName, fullName);
    }

//    @GetMapping(SEARCH)
//    public Stream<String> findNonDuplicatedHotelNamesByNumBedsAndNumBookings(@RequestParam String q) {
//        int numBeds = Integer.parseInt(new LexicalAnalyzer().extractWithAssure(q, "numBeds"));
//        int numBookings = Integer.parseInt(new LexicalAnalyzer().extractWithAssure(q, "numBookings"));
//        return this.hotelService.findNonDuplicatedHotelNamesByNumBedsAndNumBookings(numBeds, numBookings);
//    }
}
