package es.upm.miw.apaw.domain.models.videoWebsite;

import es.upm.miw.apaw.domain.models.videoWebsite.enums.VideoStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoStatusUpdating {
    @NotNull
    private UUID id;
    @NotNull
    private VideoStatus status;
}
