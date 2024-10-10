package es.upm.miw.apaw_practice.adapters.rest.hotel_retired;

import es.upm.miw.apaw_practice.domain.models.hotel_retired.Guest;
import es.upm.miw.apaw_practice.domain.services.hotel_retired.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(GuestResource.GUESTS)
public class GuestResource {

    static final String GUESTS = "/hotel-retired/guests";

    static final  String NIF_ID = "/{nif}";
    static final String SEARCH = "/search";

    private final GuestService guestService;

    @Autowired
    public GuestResource(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    public Guest create(@RequestBody Guest guest) {
        return this.guestService.create(guest);
    }

    @GetMapping(NIF_ID)
    public Guest read(@PathVariable String nif) {
        return this.guestService.read(nif);
    }
}
