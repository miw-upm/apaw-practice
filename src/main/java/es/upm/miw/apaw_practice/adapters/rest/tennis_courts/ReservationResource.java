package es.upm.miw.apaw_practice.adapters.rest.tennis_courts;

import es.upm.miw.apaw_practice.domain.services.tennis_courts.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(ReservationResource.RESERVATIONS)
public class ReservationResource {
    public static final String RESERVATIONS = "/reservations";
    public static final String OWNER_NAME = "/{ownerName}";

    private final ReservationService reservationService;

    @Autowired
    public ReservationResource(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @DeleteMapping(ReservationResource.OWNER_NAME)
    public void delete(@PathVariable String ownerName, @RequestBody LocalDateTime date){
        this.reservationService.delete(ownerName, date);
    }

}
