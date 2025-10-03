package es.upm.miw.apaw.domain.models.videogame;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    @NotNull
    @NotBlank
    private String type;
    private String description;
    private Float popularity;
    private Integer ageRestriction;

}
