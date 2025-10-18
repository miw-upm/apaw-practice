package es.upm.miw.apaw.adapters.mongodb.music.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class PlaylistEntity {
    @Id
    @Indexed(unique = true)
    @EqualsAndHashCode.Include
    private String code;
    private String label;
    private Boolean opened;
    @Builder.Default
    private List<String> songIsrcs = new ArrayList<>();
}
