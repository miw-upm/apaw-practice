package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.Director;
import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import es.upm.miw.apaw_practice.domain.services.hotel.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(DirectorResource.DIRECTORS)
public class DirectorResource {

    static final String DIRECTORS = "/hotel/directors";
    static final String DNI = "/{dni}";
    static final String HOTELGUEST = "/hotels/rooms/hotelguests/emails";

    private final DirectorService directorService;

    @Autowired
    public DirectorResource(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public List<Director> readEmails() {
        return this.directorService.readEmails();
    }

    @GetMapping(DirectorResource.DNI)
    public List<Hotel> getHotelsByDirector(@PathVariable String dni) {
        return this.directorService.getHotelsByDirector(dni);
    }

    @GetMapping(DirectorResource.DNI + DirectorResource.HOTELGUEST)
    public List<HotelGuest> findHotelGuestDistinctDni(@PathVariable String dni) {
        return this.directorService.findHotelGuestDistinctDni(dni);
    }

}
