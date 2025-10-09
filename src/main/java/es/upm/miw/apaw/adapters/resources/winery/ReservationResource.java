package es.upm.miw.apaw.adapters.resources.winery;

import es.upm.miw.apaw.domain.models.winery.Reservation;
import es.upm.miw.apaw.domain.services.winery.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ReservationResource.RESERVATION)
public class ReservationResource {

    public static final String RESERVATION = "/winery/reservation";

    private final ReservationService reservationService;

    @Autowired
    public ReservationResource(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation create(@Valid @RequestBody Reservation reservation) {
        return this.reservationService.create(reservation);
    }
}
