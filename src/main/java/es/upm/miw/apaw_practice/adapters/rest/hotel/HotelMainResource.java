package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelMain;
import es.upm.miw.apaw_practice.domain.services.hotel.HotelMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(HotelMainResource.HOTELS)
public class HotelMainResource {

    static final String HOTELS = "/hotel/hotels";

    private final HotelMainService hotelMainService;

    @Autowired
    public HotelMainResource(HotelMainService hotelMainService) {
            this.hotelMainService = hotelMainService;
        }
    @GetMapping
    public HotelMain findByName(@RequestParam String q) {
        String name = new LexicalAnalyzer().extractWithAssure(q,"name");
        return this.hotelMainService.findByName(name);
    }
}
