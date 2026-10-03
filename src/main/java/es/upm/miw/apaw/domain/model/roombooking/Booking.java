package es.upm.miw.apaw.domain.model.roombooking;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Booking {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    private Integer estimatedAttendees;

    @NotNull
    private LocalDateTime startDateTime;

    @NotNull
    private LocalDateTime endDateTime;

    private LocalDateTime createdAt;

    @NotNull
    private Room room;

    @NotNull
    private UserSnapshot userSnapshot;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    public Booking ofSummary() {
        return Booking.builder()
                .id(this.id)
                .name(this.name)
                .startDateTime(this.startDateTime)
                .endDateTime(this.endDateTime)
                .room(this.room != null ? this.room.ofSummary() : null)
                .userSnapshot(this.userSnapshot != null ? UserSnapshot.builder()
                        .id(this.userSnapshot.getId())
                        .mobile(this.userSnapshot.getMobile())
                        .firstName(this.userSnapshot.getFirstName())
                        .build() : null)
                .build();
    }
}