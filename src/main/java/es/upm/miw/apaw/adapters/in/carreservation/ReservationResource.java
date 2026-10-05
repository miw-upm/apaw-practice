package es.upm.miw.apaw.adapters.in.carreservation;

import es.upm.miw.apaw.domain.model.carreservation.Reservation;
import es.upm.miw.apaw.domain.model.carreservation.CreationReservation;
import es.upm.miw.apaw.domain.services.carreservation.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ReservationResource.RESERVATIONS)
@RequiredArgsConstructor
public class ReservationResource {

    public static final String RESERVATIONS = "/reservations";

    private final ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation create(@Valid @RequestBody CreationReservation creationReservation) {
        return this.reservationService.create(creationReservation);
    }
}