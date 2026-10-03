package es.upm.miw.apaw.domain.services.roombooking;

import es.upm.miw.apaw.adapters.out.roombooking.postgres.BookingEntity;
import es.upm.miw.apaw.adapters.out.roombooking.postgres.BookingRepository;
import es.upm.miw.apaw.adapters.out.roombooking.postgres.RoomEntity;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.roombooking.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.RoomBookingSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class RoomServiceIT {

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void testReadSeeder() {
        assertThat(this.roomService.read(ROOM_ID_0)).usingRecursiveComparison().isEqualTo(ROOM_0);
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.roomService.read(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void testFindAllAllowsAdditionalRooms() {
        Room extra = this.createRoom();
        List<Room> rooms = this.roomService.findAll();

        assertThat(rooms).extracting(Room::getId).contains(ROOM_ID_0, ROOM_ID_1, extra.getId());

        assertThat(rooms).extracting(Room::getName)
                .containsSubsequence(ROOM_0.getName(), ROOM_1.getName());
    }

    @Test
    void testCreate() {
        Room room = this.createRoom();
        Room stored = this.roomService.read(room.getId());
        assertThat(stored).usingRecursiveComparison().ignoringFields("createdAt").isEqualTo(room);
        assertThat(stored.getCreatedAt()).isNotNull();
    }

    @Test
    void testCreateDuplicateName() {
        Room room = Room.builder().name(ROOM_0.getName()).capacity(10).floor(1).build();
        assertThatThrownBy(() -> this.roomService.create(room))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(ROOM_0.getName());
    }

    @Test
    void testUpdateReplacesMutableFields() {
        Room original = this.createRoom();
        Room replacement = Room.builder()
                .name("Updated " + UUID.randomUUID())
                .capacity(50)
                .floor(3)
                .videoconferenceEquipped(true)
                .build();

        this.roomService.update(original.getId(), replacement);
        Room updated = this.roomService.read(original.getId());

        assertThat(updated.getId()).isEqualTo(original.getId());
        assertThat(updated.getName()).isEqualTo(replacement.getName());
        assertThat(updated.getCapacity()).isEqualTo(50);
        assertThat(updated.getFloor()).isEqualTo(3);
        assertThat(updated.getVideoconferenceEquipped()).isTrue();
    }

    @Test
    void testUpdateSameName() {
        Room room = this.createRoom();
        room.setCapacity(99);
        this.roomService.update(room.getId(), room);
        assertThat(this.roomService.read(room.getId()).getCapacity()).isEqualTo(99);
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.roomService.update(id, ROOM_0))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void testUpdateDuplicateNameLeavesRoomUnchanged() {
        Room room = this.createRoom();
        Room duplicateNameRequest = Room.builder().name(ROOM_0.getName()).capacity(20).floor(1).build();

        assertThatThrownBy(() -> this.roomService.update(room.getId(), duplicateNameRequest))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(ROOM_0.getName());

        assertThat(this.roomService.read(room.getId()).getName()).isEqualTo(room.getName());
    }

    @Test
    void testPatchUpdatesOnlyNonNullFields() {
        Room room = this.createRoom();
        Room patchRequest = Room.builder().capacity(150).build();

        this.roomService.patch(room.getId(), patchRequest);
        Room patched = this.roomService.read(room.getId());

        assertThat(patched.getName()).isEqualTo(room.getName()); // Intacto
        assertThat(patched.getCapacity()).isEqualTo(150);        // Actualizado
        assertThat(patched.getFloor()).isEqualTo(room.getFloor()); // Intacto
    }

    @Test
    void testPatchDuplicateName() {
        Room room = this.createRoom();
        Room patchRequest = Room.builder().name(ROOM_0.getName()).build();

        assertThatThrownBy(() -> this.roomService.patch(room.getId(), patchRequest))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(ROOM_0.getName());
    }

    @Test
    void testPatchNotFound() {
        UUID id = UUID.randomUUID();
        Room patchRequest = Room.builder().capacity(100).build();

        assertThatThrownBy(() -> this.roomService.patch(id, patchRequest))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void testDelete() {
        Room room = this.createRoom();
        this.roomService.delete(room.getId());
        assertThatThrownBy(() -> this.roomService.read(room.getId()))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteMissingRoom() {
        UUID id = UUID.randomUUID();
        this.roomService.delete(id); // No debe lanzar excepción
        assertThatThrownBy(() -> this.roomService.read(id))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteReferencedRoom() {
        Room room = this.createRoom();
        BookingEntity booking = BookingEntity.builder()
                .id(UUID.randomUUID())
                .name("Meeting " + UUID.randomUUID())
                .estimatedAttendees(10)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusHours(1))
                .room(new RoomEntity(room))
                .userId(UUID.randomUUID())
                .build();
        this.bookingRepository.saveAndFlush(booking);

        assertThatThrownBy(() -> this.roomService.delete(room.getId()))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(room.getId().toString());

        assertThat(this.roomService.read(room.getId()).getId()).isEqualTo(room.getId());
    }

    private Room createRoom() {
        return this.roomService.create(Room.builder()
                .name("IT Room " + UUID.randomUUID())
                .capacity(20)
                .floor(1)
                .videoconferenceEquipped(false)
                .build());
    }
}