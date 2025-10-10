package es.upm.miw.apaw.adapters.mongodb.metro.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class TrainLineEntity {

    @Id
    private Integer number;

    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String color;

    private Integer numStations;

    private Boolean circular;

    @DBRef
    private List<TrainEntity> trainEntities;
}
