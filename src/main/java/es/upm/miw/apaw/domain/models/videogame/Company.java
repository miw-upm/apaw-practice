package es.upm.miw.apaw.domain.models.videogame;

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
public class Company {

    @NotNull
    @NotBlank
    private String denomination;
    @NotNull
    private LocalDate foundationDate;
    @NotNull
    @NotBlank
    private String sector;
    private List<Videogame> videoGames;
}

