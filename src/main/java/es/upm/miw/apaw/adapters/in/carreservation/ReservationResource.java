package es.upm.miw.apaw.adapters.in.carreservation;

import es.upm.miw.apaw.domain.model.carreservation.CarUsageReport;
import es.upm.miw.apaw.domain.model.carreservation.Reservation;
import es.upm.miw.apaw.domain.model.carreservation.CreationReservation;
import es.upm.miw.apaw.domain.model.carreservation.ReservationFindCriteria;
import es.upm.miw.apaw.domain.services.carreservation.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ReservationResource.RESERVATIONS)
@RequiredArgsConstructor
public class ReservationResource {

    public static final String RESERVATIONS = "/reservations";
    public static final String REPORT = "/report";

    private final ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation create(@Valid @RequestBody CreationReservation creationReservation) {
        return this.reservationService.create(creationReservation);
    }

    @GetMapping(REPORT)
    public List<CarUsageReport> findCarUsageReport() {
        return this.reservationService.findCarUsageReport();
    }

    @GetMapping
    public List<Reservation> find(ReservationFindCriteria criteria) {
        return this.reservationService.find(criteria);
    }
}