package es.upm.miw.apaw.domain.models.airport;

import es.upm.miw.apaw.domain.models.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @NotNull
    @NotBlank
    private UUID id;
    @NotNull
    @NotBlank
    private LocalDateTime departureTime;
    @NotNull
    @NotBlank
    private LocalDateTime arrivalTime;
    @NotNull
    @NotBlank
    private String destination;

    private BoardingGate boardingGate;
    private Plane plane;
    private List<UserDto> passengers;
    private UserDto pilot;
}
