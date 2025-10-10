package es.upm.miw.apaw.adapters.mongodb.metro.entities;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class TrainStationEntity {

    @Id
    private String name;

    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private Integer capacity;

    private String location;

    private Boolean multipleLines;

    private LocalDate inaugurationDate;

    @DBRef
    private List<TrainLineEntity> trainLineEntities;
    private String zoneType;
    private List<UUID> users;
}
