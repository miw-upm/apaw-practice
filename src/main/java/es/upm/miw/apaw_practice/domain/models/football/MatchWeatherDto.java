package es.upm.miw.apaw_practice.domain.models.football;

import java.time.LocalDateTime;

public class MatchWeatherDto {
    private LocalDateTime date;
    private String weather;

    public MatchWeatherDto() {
        //empty for framework
    }

    public MatchWeatherDto(LocalDateTime date, String weather) {
        this.date = date;
        this.weather = weather;
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

    @Override
    public String toString() {
        return "MatchWeatherDto{" +
                "date=" + date +
                ", weather='" + weather + '\'' +
                '}';
    }
}
