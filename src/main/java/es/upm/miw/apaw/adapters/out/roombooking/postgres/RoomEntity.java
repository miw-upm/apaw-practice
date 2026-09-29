package es.upm.miw.apaw.adapters.out.roombooking.postgres;

import es.upm.miw.apaw.domain.model.roombooking.Room;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class RoomEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Integer floor;

    @Column(nullable = false)
    private Boolean videoconferenceEquipped;

    private LocalDateTime createdAt;

    public RoomEntity(Room room) {
        BeanUtils.copyProperties(room, this);
    }

    public Room toDomain() {
        Room room = new Room();
        BeanUtils.copyProperties(this, room);
        return room;
    }
}