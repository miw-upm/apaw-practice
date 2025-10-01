package es.upm.miw.apaw.adapters.mongodb.airport.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class AirlineEntity {
    @Id
    private UUID id;
    @Indexed(unique = true)
    private String name;
    private String code;
    private String country;

    @DBRef
    private List<FlightEntity> flights;
}
