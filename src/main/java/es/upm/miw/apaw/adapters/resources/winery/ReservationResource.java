package es.upm.miw.apaw.adapters.resources.winery;

import es.upm.miw.apaw.domain.models.winery.Reservation;
import es.upm.miw.apaw.domain.services.winery.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ReservationResource.RESERVATION)
public class ReservationResource {

    public static final String RESERVATION = "/winery/reservation";

    public static final String SEARCH_BY_WINE_NAME = "/searchByWineName";

    private final ReservationService reservationService;

    @Autowired
    public ReservationResource(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation create(@Valid @RequestBody Reservation reservation) {
        return this.reservationService.create(reservation);
    }

    @GetMapping(SEARCH_BY_WINE_NAME)
    public List<UUID> findReservationIdsByWineName(@RequestParam String name) {
        return this.reservationService.findReservationIdsByWineName(name);
    }

}
