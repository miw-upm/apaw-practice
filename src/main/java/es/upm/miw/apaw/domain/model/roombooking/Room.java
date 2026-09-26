package es.upm.miw.apaw.domain.model.roombooking;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

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
}