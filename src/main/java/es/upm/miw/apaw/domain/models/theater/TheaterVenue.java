package es.upm.miw.apaw.domain.models.theater;

import es.upm.miw.apaw.domain.models.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterVenue {
    @NotNull
    @NotBlank
    private String venueCode;
    @NotNull
    @NotBlank
    private String venueName;
    @NotNull
    @NotBlank
    private String venueCity;
    @NotNull
    private Boolean venueOpen;
    @NotNull
    private LocalDateTime venueCreatedAt;
    @NotNull
    private List<TheaterHall> venueHalls;
    @NotNull
    private UserDto venueManager;
}
