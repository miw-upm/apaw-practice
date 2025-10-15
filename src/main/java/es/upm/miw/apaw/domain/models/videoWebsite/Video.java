package es.upm.miw.apaw.domain.models.videoWebsite;

import es.upm.miw.apaw.domain.models.videoWebsite.enums.VideoStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Video {
    private UUID id;
    @NotBlank
    private String title;
    private String description;
    private LocalDateTime uploadDate;
    @NotNull
    private VideoStatus videoStatus;
    private Integer views;

}
