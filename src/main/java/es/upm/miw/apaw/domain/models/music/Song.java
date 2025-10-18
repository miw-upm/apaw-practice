package es.upm.miw.apaw.domain.models.music;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    @NotNull
    @NotBlank
    @Pattern(
            regexp = "^[A-Z]{2}[A-Z0-9]{3}\\d{7}$",
            message = "ISRC must match CCXXXYYNNNNN (12 chars, uppercase)"
    )
    private String isrc;
    @NotNull
    @NotBlank
    private String title;
    @Positive
    private Integer durationSeconds;
    @NotNull
    private Style style;
}