package es.upm.miw.apaw_practice.adapters.rest.tennis_courts;

import es.upm.miw.apaw_practice.domain.services.tennis_courts.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ReservationResource.RESERVATIONS)
public class ReservationResource {
    public static final String RESERVATIONS = "/reservations";
    public static final String OWNER_NAME = "/{ownerName}";
    public static final String DATE = "/{stringDate}"; //El formato del string de la fecha ha de ser DD:MM:AA
    public static final String TIME = "/{stringTime}"; //El formato del string de la hora ha de ser HH:MM

    private final ReservationService reservationService;

    @Autowired
    public ReservationResource(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @DeleteMapping(ReservationResource.OWNER_NAME + ReservationResource.DATE + ReservationResource.TIME)
    public void delete(@PathVariable String ownerName, @PathVariable String stringDate, @PathVariable String stringTime){
        this.reservationService.delete(ownerName, stringDate, stringTime);
    }

}
