package es.upm.miw.apaw.domain.models.recruiting;

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
public class Meeting {
    @NotNull
    @NotBlank
    private LocalDateTime date;
    private String url;

    // Optional Aggregation with multiple Attendees
    private List<Attendee> attendees;
}