package es.upm.miw.apaw.domain.models.music;

import es.upm.miw.apaw.domain.models.UserDto;
import jakarta.validation.constraints.NotBlank;
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
public class Artist {
    @NotNull
    private UUID artistId;

    @NotNull
    @NotBlank
    private String artistName;

    private LocalDate activeSince;

    @NotNull
    private UserDto linkedUser;

    @NotNull
    private List<Song> artistSongs; // composición 1..*
}