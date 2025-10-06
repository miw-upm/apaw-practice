package es.upm.miw.apaw.domain.models.football;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FootballPlayer {
    private Long playerId;
    private String nickname;
    private LocalDate birthDate;
    private Integer goalsScored;

    // Relationship
    private FootballClub club;
}
