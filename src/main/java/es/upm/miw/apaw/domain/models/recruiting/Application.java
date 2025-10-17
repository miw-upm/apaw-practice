package es.upm.miw.apaw.domain.models.recruiting;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.recruiting.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @NotNull
    private UUID id;
    @NotNull
    private Status status;
    @NotNull
    private LocalDate created;
    private Boolean referral;

    // Mandatory Aggregation with one UserDto
    @NotNull
    private UserDto user;

    // Mandatory Aggregation with one Position
    @NotNull
    private Position position;

    // Optional Composition with multiple Meetings
    @Builder.Default
    private List<Meeting> meetingList = new ArrayList<>();
}