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
public class CreationBooking {

    @NotBlank
    private String name;

    @NotNull
    private Integer estimatedAttendees;

    @NotNull
    private LocalDateTime startDateTime;

    @NotNull
    private LocalDateTime endDateTime;

    @NotNull
    private UUID roomId;

    @NotNull
    private UUID userId;
}