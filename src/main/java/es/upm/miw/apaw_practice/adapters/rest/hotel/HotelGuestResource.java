package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import es.upm.miw.apaw_practice.domain.services.hotel.HotelGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(HotelGuestResource.HOTELGUESTS)
public class HotelGuestResource {

    static final String HOTELGUESTS = "/hotel/hotelguests";
    static final String DNI = "/{dni}";

    private final HotelGuestService hotelGuestService;

    @Autowired
    public HotelGuestResource(HotelGuestService hotelGuestService) {
        this.hotelGuestService = hotelGuestService;
    }

    @PostMapping
    public HotelGuest create(@RequestBody HotelGuest hotelGuest) {
        return this.hotelGuestService.create(hotelGuest);
    }

    @GetMapping(HotelGuestResource.DNI)
    public HotelGuest readByDni(@PathVariable String dni) {
        return this.hotelGuestService.readByDni(dni);
    }

    @DeleteMapping(HotelGuestResource.DNI)
    public void delete(@PathVariable String dni) {
        this.hotelGuestService.delete(dni);
    }

}
