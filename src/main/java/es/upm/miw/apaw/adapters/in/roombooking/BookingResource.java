package es.upm.miw.apaw.adapters.in.roombooking;

import es.upm.miw.apaw.domain.model.roombooking.Booking;
import es.upm.miw.apaw.domain.model.roombooking.CreationBooking;
import es.upm.miw.apaw.domain.services.roombooking.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BookingResource.BOOKINGS)
@RequiredArgsConstructor
public class BookingResource {

    public static final String BOOKINGS = "/room-booking/bookings";

    private final BookingService bookingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking create(@Valid @RequestBody CreationBooking creationBooking) {
        return this.bookingService.create(creationBooking);
    }
}