package es.upm.miw.apaw_practice.adapters.rest.car_hire;

import es.upm.miw.apaw_practice.domain.services.car_hire.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookingResource.BOOKING)
public class BookingResource {
    static final String BOOKING = "/car-hire/bookings";

    static final String BOOKING_NUMBER = "/{bookingNumber}";

    private final BookingService bookingService;

    @Autowired
    public BookingResource(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @DeleteMapping(BookingResource.BOOKING_NUMBER)
    public void delete(@PathVariable String bookingNumber) {
        this.bookingService.delete(bookingNumber);
    }
}
