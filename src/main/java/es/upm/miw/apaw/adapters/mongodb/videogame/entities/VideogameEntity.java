package es.upm.miw.apaw.adapters.mongodb.videogame.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideogameEntity {
    @Id
    private UUID id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String name;
    private Integer maxPlayers;
    private Boolean online;
    private LocalDate releaseDate;
    @DBRef
    private GenreEntity genreEntity;

}
