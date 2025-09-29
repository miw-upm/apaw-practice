package es.upm.miw.apaw.domain.models.recruiting;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.recruiting.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @NotNull
    @NotBlank
    private UUID id;
    @NotNull
    @NotBlank
    private Status status;
    @NotNull
    @NotBlank
    private LocalDate date;

    // Mandatory Aggregation with one UserDto
    @NotNull
    @NotBlank
    private UserDto user;

    // Mandatory Aggregation with one Position
    @NotNull
    @NotBlank
    private Position position;

    // Optional Composition with multiple Meetings
    private List<Meeting> meetingList;
}