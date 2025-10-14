package es.upm.miw.apaw.adapters.mongodb.football.entities;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "stadium")
public class StadiumEntity {
    @Id
    private String id;

    @Indexed(unique = true)
    private Long stadiumId;

    private String officialName;
    private Integer capacity;
    private Boolean roof;
}

