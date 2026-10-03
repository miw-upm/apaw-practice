package es.upm.miw.apaw.adapters.in.roombooking;

import es.upm.miw.apaw.domain.model.roombooking.Booking;
import es.upm.miw.apaw.domain.model.roombooking.BookingFindCriteria;
import es.upm.miw.apaw.domain.model.roombooking.CreationBooking;
import es.upm.miw.apaw.domain.model.roombooking.UserBookingReport;
import es.upm.miw.apaw.domain.services.roombooking.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BookingResource.BOOKINGS)
@RequiredArgsConstructor
public class BookingResource {

    public static final String BOOKINGS = "/room-booking/bookings";
    public static final String REPORT = "/report";

    private final BookingService bookingService;

    @GetMapping
    public List<Booking> find(@ModelAttribute BookingFindCriteria criteria) {
        return this.bookingService.find(criteria);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking create(@Valid @RequestBody CreationBooking creationBooking) {
        return this.bookingService.create(creationBooking);
    }

    @GetMapping(REPORT)
    public List<UserBookingReport> findUserBookingReports() {
        return this.bookingService.findUserBookingReports();
    }
}