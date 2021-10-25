package es.upm.miw.apaw_practice.domain.models.football;

import java.time.LocalDateTime;
import java.util.List;

public class Match {
    private LocalDateTime date;
    private String weather;
    private Integer round;
    private PrincipalReferee principalReferee;
    private List<FootballPlayer> players;

    public Match() {
        //Empty for framework
    }

    public static MatchBuilders.Date builder(){
        return new Builder();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public PrincipalReferee getPrincipalReferee() {
        return principalReferee;
    }

    public void setPrincipalReferee(PrincipalReferee principalReferee) {
        this.principalReferee = principalReferee;
    }

    public List<FootballPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<FootballPlayer> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Match{" +
                "date=" + date +
                ", weather=" + weather +
                ", round=" + round +
                '}';
    }

    public static class Builder implements MatchBuilders.Date, MatchBuilders.Weather, MatchBuilders.Round, MatchBuilders.PrincipalReferee, MatchBuilders.Players, MatchBuilders.MatchBuild{
        private final Match match;

        public Builder(){
            this.match = new Match();
        }

        @Override
        public MatchBuilders.Weather date(LocalDateTime date) {
            this.match.date = date;
            return this;
        }

        @Override
        public MatchBuilders.Round weather(String weather) {
            this.match.weather = weather;
            return this;
        }

        @Override
        public MatchBuilders.PrincipalReferee round(Integer round) {
            this.match.round = round;
            return this;
        }

        @Override
        public MatchBuilders.Players principalReferee(PrincipalReferee principalReferee) {
            this.match.principalReferee = principalReferee;
            return this;
        }

        @Override
        public MatchBuilders.MatchBuild players(List<FootballPlayer> players) {
            this.match.players = players;
            return this;
        }

        @Override
        public Match build() {
            return this.match;
        }
    }
}
