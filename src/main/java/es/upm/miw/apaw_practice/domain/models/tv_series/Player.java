package es.upm.miw.apaw_practice.domain.models.tv_series;

import java.time.LocalDate;
import java.util.List;

public class Player {
    private String name;
    private LocalDate birth;
    private String nationality;
    private List<TvSeries> tvSeries;

    public Player() {
        // empty for framework
    }

    public Player(String name, LocalDate birth, String nationality, List<TvSeries> tvSeries) {
        this.name = name;
        this.birth = birth;
        this.nationality = nationality;
        this.tvSeries = tvSeries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<TvSeries> getTvSeries() {
        return tvSeries;
    }

    public void setTvSeries(List<TvSeries> tvSeries) {
        this.tvSeries = tvSeries;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", birth=" + birth +
                ", nationality='" + nationality + '\'' +
                ", tvSeries=" + tvSeries +
                '}';
    }
}
