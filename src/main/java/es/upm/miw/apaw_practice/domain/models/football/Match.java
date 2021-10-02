package es.upm.miw.apaw_practice.domain.models.football;

import java.time.LocalDateTime;

public class Match {
    private LocalDateTime date;
    private String weather;
    private int round;

    public Match() {
        //Empty for framework
    }

    public Match(LocalDateTime date, String weather, int round) {
        this.date = date;
        this.weather = weather;
        this.round = round;
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

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
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
