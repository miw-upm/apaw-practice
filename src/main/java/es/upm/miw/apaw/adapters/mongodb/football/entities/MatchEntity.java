package es.upm.miw.apaw.adapters.mongodb.football.entities;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "match")
public class MatchEntity {
    @Id
    private String id;

    @Indexed(unique = true)
    private Long matchId;

    private LocalDateTime dateTime;
    private Integer homeGoals;
    private Integer awayGoals;

    @DBRef
    private List<FootballClubEntity> clubs;
}
