package es.upm.miw.apaw.domain.models.recruiting;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {
    @NotNull
    private LocalDateTime date;
    private String url;

    // Optional Aggregation with multiple Attendees
    @Builder.Default
    private List<Attendee> attendees = new ArrayList<>();
}