package es.upm.miw.apaw.adapters.mongodb.videogame.entities;


import es.upm.miw.apaw.domain.models.videogame.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;


@Builder
@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class GenreEntity {
    @Id
    private UUID id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String type;
    private String description;
    private Float popularity;
    private Integer ageRestriction;

    public GenreEntity() {
        if (this.id == null) {

            this.id = UUID.randomUUID();
        }
    }

    public Genre toGenre() {
        Genre genre = new Genre();
        BeanUtils.copyProperties(this, genre);
        return genre;
    }
}
