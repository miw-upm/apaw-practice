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

    public Match(LocalDateTime date, String weather, Integer round, PrincipalReferee principalReferee, List<FootballPlayer> players) {
        this.date = date;
        this.weather = weather;
        this.round = round;
        this.principalReferee = principalReferee;
        this.players = players;
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
}
