package es.upm.miw.apaw.adapters.mongodb.music.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class ArtistEntity {
    @Id
    @Indexed(unique = true)
    @EqualsAndHashCode.Include
    private String name;
    private LocalDate activeSince;
    private Long monthlyListeners;
    private String userId;
    private List<String> songIsrcs;
}