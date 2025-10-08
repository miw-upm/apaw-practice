package es.upm.miw.apaw.domain.models.videoWebsite;

import es.upm.miw.apaw.domain.models.videoWebsite.enums.VideoStatus;
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

public class Video {
    private UUID id;
    private String title;
    private String description;
    private LocalDate UploadDate;
    private VideoStatus videoStatus;
    @NotNull
    private WebAccount webAccount;

}
