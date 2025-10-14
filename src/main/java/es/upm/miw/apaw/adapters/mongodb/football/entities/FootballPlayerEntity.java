package es.upm.miw.apaw.adapters.mongodb.football.entities;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "football_player")
public class FootballPlayerEntity {
    @Id
    private String id;

    @Indexed(unique = true)
    private Long playerId;

    private String nickname;
    private LocalDate birthDate;
    private Integer goalsScored;
}

