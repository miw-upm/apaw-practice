package es.upm.miw.apaw_practice.adapters.rest.car_hire;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.car_hire.Renter;
import es.upm.miw.apaw_practice.domain.models.car_hire.Vehicle;
import es.upm.miw.apaw_practice.domain.services.car_hire.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping(BookingResource.BOOKING)
public class BookingResource {
    static final String BOOKING = "/car-hire/bookings";

    static final String BOOKING_NUMBER = "/{bookingNumber}";
    static final String RENTERS = "/renters";
    static final String RENTERS_NAME = "/{name}";
    static final String VEHICLES = "/vehicles";
    static final String SEARCH = "/search";

    private final BookingService bookingService;

    @Autowired
    public BookingResource(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @DeleteMapping(BookingResource.BOOKING_NUMBER)
    public void delete(@PathVariable String bookingNumber) {
        this.bookingService.delete(bookingNumber);
    }

    @GetMapping(BookingResource.RENTERS + BookingResource.RENTERS_NAME)
    public Stream<Vehicle> getVehiclesVinNumberByRentersName(@PathVariable String name) {
        return this.bookingService.getVehiclesVinNumberByRentersName(name)
                .map(Vehicle::ofIdVinNumber);
    }

    @GetMapping(BookingResource.VEHICLES + BookingResource.SEARCH)
    public Set<Renter> getRentersNameByModelType(@RequestParam String q) {
        String type = new LexicalAnalyzer().extractWithAssure(q, "Model_Type:");
        return this.bookingService.getRentersNameByModelType(type);
    }
}
