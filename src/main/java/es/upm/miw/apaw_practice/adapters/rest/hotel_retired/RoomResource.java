package es.upm.miw.apaw_practice.adapters.rest.hotel_retired;

import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import es.upm.miw.apaw_practice.domain.services.hotel_retired.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RoomResource.ROOMS)
public class RoomResource {

    static final String ROOMS = "/hotel-retired/rooms";

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
}
