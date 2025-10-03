package es.upm.miw.apaw.domain.models.music;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private String isrc;
    @NotNull
    @NotBlank
    private String title;
    private Integer durationSeconds;
    @NotNull
    private Style style;
}