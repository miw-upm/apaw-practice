package es.upm.miw.apaw_practice.domain.models.tv_series;

public class Episode {
    private Integer number;
    private Integer season;
    private Integer duration;

    public Episode() {
        // empty for framework
    }

    public Episode(Integer number, Integer season, Integer duration) {
        this.number = number;
        this.season = season;
        this.duration = duration;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "number=" + this.number +
                ", season=" + this.season +
                ", duration=" + this.duration +
                '}';
    }
}
