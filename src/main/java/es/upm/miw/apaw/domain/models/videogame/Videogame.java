package es.upm.miw.apaw.domain.models.videogame;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Videogame {
    @NotNull
    @NotBlank
    private String name;
    private Integer maxPlayers;
    private Boolean online;
    private LocalDate releaseDate;
    @NotNull
    private Company company;
    @NotNull
    private Genre genre;

}
