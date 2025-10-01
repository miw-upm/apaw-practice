package es.upm.miw.apaw.domain.models.fighters;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Rating {
    @NotNull
    private UUID idRating;
    @Min(0)
    @Max(5)
    private int score;
    private String comment;
    @NotNull
    private LocalDateTime createdAt;
}
