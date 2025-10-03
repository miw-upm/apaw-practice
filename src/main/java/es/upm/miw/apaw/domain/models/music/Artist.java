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

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    @NotNull
    @NotBlank
    private String name;
    private LocalDate activeSince;
    private Long monthlyListeners;
    @NotNull
    private UserDto user;
    @NotNull
    private List<Song> songs;
}