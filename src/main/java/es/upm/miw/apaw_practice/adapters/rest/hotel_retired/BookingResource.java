package es.upm.miw.apaw_practice.adapters.rest.hotel_retired;

import es.upm.miw.apaw_practice.domain.models.hotel_retired.Booking;
import es.upm.miw.apaw_practice.domain.services.hotel_retired.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookingResource.BOOKINGS)
public class BookingResource {

    static final String BOOKINGS = "/hotel-retired/bookings";

    static final String SEARCH = "/search";

    private final BookingService bookingService;

    @Autowired
    public BookingResource(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking create(@RequestBody Booking booking) {
        return this.bookingService.create(booking);
    }
}
