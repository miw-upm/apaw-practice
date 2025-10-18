package es.upm.miw.apaw.adapters.mongodb.videogame.entities;

import es.upm.miw.apaw.domain.models.videogame.Videogame;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
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

    public VideogameEntity() {
        if (this.id == null) {  // Generar UUID automáticamente
            this.id = UUID.randomUUID();
        }
    }

    public Videogame toVideogame() {
        Videogame videogame = new Videogame();
        BeanUtils.copyProperties(this, videogame);
        return videogame;
    }

    public void fromVideogame(Videogame videogame) {
        BeanUtils.copyProperties(videogame, this);
    }

}
