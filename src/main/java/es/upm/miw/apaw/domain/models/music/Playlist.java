package es.upm.miw.apaw.domain.models.music;

import jakarta.validation.constraints.NotBlank;
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
public class Playlist {
    @NotNull
    private UUID playlistId;

    @NotNull
    @NotBlank
    private String playlistTitle;

    private Boolean isPublic;

    @NotNull
    private List<Song> playlistSongs; // agregación n..n
}