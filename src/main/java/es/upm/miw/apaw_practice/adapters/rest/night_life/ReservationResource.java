package es.upm.miw.apaw_practice.adapters.rest.night_life;
import es.upm.miw.apaw_practice.domain.models.night_life.Reservation;
import es.upm.miw.apaw_practice.domain.services.night_life.ClubService;
import es.upm.miw.apaw_practice.domain.services.night_life.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ReservationResource.RESERVATIONS)
public class ReservationResource {
    static final String RESERVATIONS = "/night-life/reservations";
    private final ReservationService reservationService;
    static final String ID_ID = "/{id}";

    @Autowired
    public ReservationResource(ReservationService reservationService) {
       this.reservationService = reservationService;
    }




}
