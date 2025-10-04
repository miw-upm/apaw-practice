package es.upm.miw.apaw.adapters.mongodb.videogame.entities;

import es.upm.miw.apaw.domain.models.videogame.Videogame;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideogameEntity {
    private String name;
    private Integer maxPlayers;
    private Boolean online;
    private LocalDate releaseDate;
    @DBRef
    private GenreEntity genreEntity;

}
