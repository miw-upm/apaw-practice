package es.upm.miw.apaw.adapters.in.roombooking;

import es.upm.miw.apaw.domain.model.roombooking.Room;
import es.upm.miw.apaw.domain.services.roombooking.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(RoomResource.ROOMS)
@RequiredArgsConstructor
public class RoomResource {

    public static final String ROOMS = "/room-booking/rooms";
    public static final String ID = "/{id}";

    private final RoomService roomService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room create(@Valid @RequestBody Room room) {
        return this.roomService.create(room);
    }

    @GetMapping
    public List<Room> findAll() {
        return this.roomService.findAll();
    }

    @GetMapping(ID)
    public Room read(@PathVariable UUID id) {
        return this.roomService.read(id);
    }

    @PutMapping(ID)
    public Room update(@PathVariable UUID id, @Valid @RequestBody Room room) {
        return this.roomService.update(id, room);
    }

    @PatchMapping(ID)
    public Room patch(@PathVariable UUID id, @RequestBody Room room) {
        return this.roomService.patch(id, room);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.roomService.delete(id);
    }
}