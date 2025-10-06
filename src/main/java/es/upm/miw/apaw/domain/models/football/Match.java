package es.upm.miw.apaw.domain.models.football;

import java.time.LocalDateTime;
import java.util.List;

public class Match
{
    private Long matchId;
    private LocalDateTime dateTime;
    private Integer homeGoals;
    private Integer awayGoals;

    private List<FootballClub> clubs;
}
