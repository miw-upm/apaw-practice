package es.upm.miw.apaw_practice.domain.models.football;

import java.time.LocalDateTime;
import java.util.List;

public interface MatchBuilders {

    interface Date{
        Weather date(LocalDateTime date);
    }

    interface Weather{
        Round weather(String weather);
    }

    interface Round{
        PrincipalReferee round(Integer round);
    }

    interface PrincipalReferee{
        Players principalReferee(es.upm.miw.apaw_practice.domain.models.football.PrincipalReferee principalReferee);
    }

    interface Players{
        MatchBuild players(List<FootballPlayer> players);
    }

    interface MatchBuild{
        Match build();
    }
}
