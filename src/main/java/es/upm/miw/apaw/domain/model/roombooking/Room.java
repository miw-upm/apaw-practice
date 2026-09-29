package es.upm.miw.apaw.domain.model.roombooking;

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
public class Room {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    private Integer capacity;

    @NotNull
    private Integer floor;

    @Builder.Default
    private Boolean videoconferenceEquipped = false;

    private LocalDateTime createdAt;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    public Room ofSummary() {
        return Room.builder()
                .id(this.id)
                .name(this.name)
                .capacity(this.capacity)
                .floor(this.floor)
                .build();
    }
}