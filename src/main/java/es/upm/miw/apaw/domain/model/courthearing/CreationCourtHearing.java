package es.upm.miw.apaw.domain.model.courthearing;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class CreationCourtHearing {

    @NotNull
    private LocalDateTime date;

    @NotBlank
    private String roomNumber;

    @Positive
    private Integer durationMinutes;

    private Boolean openToPublic;

    private Boolean remote;

    @NotNull
    private CourtHearingType type;

    @NotNull
    private UUID courtId;

    @NotEmpty
    private List<@NotNull UUID> attendeeIds;
}