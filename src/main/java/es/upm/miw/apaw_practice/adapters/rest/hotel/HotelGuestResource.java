package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import es.upm.miw.apaw_practice.domain.services.hotel.HotelGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(HotelGuestResource.HOTELGUEST)
public class HotelGuestResource {

    static final String HOTELGUEST = "/hotel/hotelguests";

    private final HotelGuestService hotelGuestService;

    @Autowired
    public HotelGuestResource(HotelGuestService hotelGuestService) {
        this.hotelGuestService = hotelGuestService;
    }

    @PostMapping
    public HotelGuest create(@RequestBody HotelGuest hotelGuest) {
            return this.hotelGuestService.create(hotelGuest);

    }
}
