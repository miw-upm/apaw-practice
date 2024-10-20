package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.HotelClient;
import es.upm.miw.apaw_practice.domain.services.hotel.HotelClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(HotelClientResource.CLIENTS)
public class HotelClientResource {
    static final String CLIENTS = "/hotel/clients";
    private final HotelClientService hotelClientService;

    @Autowired
    public HotelClientResource(HotelClientService hotelClientService) {
        this.hotelClientService = hotelClientService;
    }

    @PostMapping
    public HotelClient create(@RequestBody HotelClient client) {
        return this.hotelClientService.create(client);
    }
}