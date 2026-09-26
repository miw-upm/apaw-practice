package es.upm.miw.apaw.domain.model.roombooking;

import es.upm.miw.apaw.domain.model.UserSnapshot;
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
public class Booking {

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
}