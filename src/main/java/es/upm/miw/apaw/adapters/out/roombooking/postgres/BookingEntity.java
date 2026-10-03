package es.upm.miw.apaw.adapters.out.roombooking.postgres;

import es.upm.miw.apaw.domain.model.roombooking.Booking;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookingEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer estimatedAttendees;

    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @Column(nullable = false)
    private LocalDateTime endDateTime;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private RoomEntity room;

    @Column(nullable = false)
    private UUID userId;

    public BookingEntity(Booking booking) {
        BeanUtils.copyProperties(booking, this, "room", "userSnapshot");
        if (booking.getRoom() != null) {
            this.room = new RoomEntity(booking.getRoom());
        }
        if (booking.getUserSnapshot() != null) {
            this.userId = booking.getUserSnapshot().getId();
        }
    }

    public Booking toDomain() {
        Booking booking = new Booking();
        BeanUtils.copyProperties(this, booking, "room", "userId");
        if (this.room != null) {
            booking.setRoom(this.room.toDomain());
        }
        if (this.userId != null) {
            booking.setUserSnapshot(UserSnapshot.builder().id(this.userId).build());
        }
        return booking;
    }
}