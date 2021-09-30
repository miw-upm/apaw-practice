package es.upm.miw.apaw_practice.domain.models.tv_series;

import java.util.List;

public class TvSeries {
    private String title;
    private Integer year;
    private Boolean finished;
    private Producer producer;
    private List<Episode> episodes;

    public TvSeries() {
        // empty for framework
    }

    public TvSeries(String title, Integer year, Boolean finished, Producer producer, List<Episode> episodes) {
        this.title = title;
        this.year = year;
        this.finished = finished;
        this.producer = producer;
        this.episodes = episodes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Producer getProducer() {
        return this.producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    @Override
    public String toString() {
        return "TvSeries{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", finished=" + finished +
                ", producer=" + producer +
                ", episodes=" + episodes +
                '}';
    }
}