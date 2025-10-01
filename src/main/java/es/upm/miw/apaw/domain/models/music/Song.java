package es.upm.miw.apaw.domain.models.music;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    @NotNull
    private UUID songId;

    @NotNull
    @NotBlank
    private String songTitle;

    private Integer durationSeconds;

    @NotNull
    private Style songStyle; // agregación n..1
}