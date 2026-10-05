package es.upm.miw.apaw.domain.model.meeting;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class CreationMeeting {

    @NotBlank
    private String title;

    @NotNull
    private LocalDateTime meetingDate;

    private String location;

    @NotNull
    private Integer durationMinutes;

    private Boolean online;

    private String description;

    @NotEmpty
    private List<@NotNull UUID> legalIssueIds;

    @NotNull
    private List<@NotNull UUID> participantIds;
}
