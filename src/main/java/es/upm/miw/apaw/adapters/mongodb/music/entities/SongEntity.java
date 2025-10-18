package es.upm.miw.apaw.adapters.mongodb.music.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class SongEntity {
    @Id
    @Indexed(unique = true)
    @EqualsAndHashCode.Include
    private String isrc;
    private String title;
    private Integer durationSeconds;
    private String artistName;
    private String styleGenre;
}
