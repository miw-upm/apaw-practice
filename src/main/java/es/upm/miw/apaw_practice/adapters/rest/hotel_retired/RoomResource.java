package es.upm.miw.apaw_practice.adapters.rest.hotel_retired;

import es.upm.miw.apaw_practice.domain.models.hotel_retired.Booking;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import es.upm.miw.apaw_practice.domain.services.hotel_retired.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RoomResource.ROOMS)
public class RoomResource {

    static final String ROOMS = "/hotel-retired/rooms";

    static final String NUM_ID = "/{num}";
    static final String BOOKINGS = "/bookings";
    static final String SEARCH = "/search";

    private final RoomService roomService;

    @Autowired
    public RoomResource(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public Room create(@RequestBody Room room) {
        return this.roomService.create(room);
    }

    @GetMapping(NUM_ID)
    public Room read(@PathVariable String num) {
        return this.roomService.read(num);
    }

    @DeleteMapping(NUM_ID)
    public void delete(@PathVariable String num) {
        this.roomService.delete(num);
    }

    @PutMapping(NUM_ID)
    public Room update(@PathVariable String num, @RequestBody Room room) {
        return this.roomService.update(num, room);
    }

    @PatchMapping(NUM_ID + BOOKINGS)
    public Room updateBookings(@PathVariable String num, @RequestBody List<Booking> bookings) {
        return this.roomService.updateBookings(num, bookings);
    }
}
