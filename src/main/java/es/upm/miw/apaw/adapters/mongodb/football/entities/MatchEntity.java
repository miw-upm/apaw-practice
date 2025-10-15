package es.upm.miw.apaw.adapters.mongodb.football.entities;

import es.upm.miw.apaw.domain.models.football.Match;
import es.upm.miw.apaw.domain.models.football.FootballClub;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MatchEntity {

    private static final String[] IGNORE_PROPERTIES = {"clubs"};

    @Id
    @EqualsAndHashCode.Include
    private Long matchId;
    private LocalDateTime dateTime;
    private Integer homeGoals;
    private Integer awayGoals;

    @DBRef
    private List<FootballClubEntity> clubs = new ArrayList<>();

    public MatchEntity(Match match) {
        BeanUtils.copyProperties(match, this, IGNORE_PROPERTIES);
        if (match.getClubs() != null) {
            this.clubs = match.getClubs().stream()
                    .map(FootballClubEntity::new)
                    .toList();
        }
    }

    public Match toMatch() {
        Match match = new Match();
        BeanUtils.copyProperties(this, match, IGNORE_PROPERTIES);
        if (this.clubs != null) {
            List<FootballClub> clubsList = this.clubs.stream()
                    .map(FootballClubEntity::toFootballClub)
                    .toList();
            match.setClubs(clubsList);
        }
        return match;
    }
}
