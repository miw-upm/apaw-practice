package es.upm.miw.apaw.domain.models.videoWebsite;

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
public class Comment {
    @NotBlank
    private String content;
    private LocalDateTime postTime;

    @NotNull
    private WebAccount commenter;
}
