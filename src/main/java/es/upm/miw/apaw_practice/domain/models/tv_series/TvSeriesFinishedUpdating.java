package es.upm.miw.apaw_practice.domain.models.tv_series;

public class TvSeriesFinishedUpdating {
    private String title;
    private Boolean finished;

    public TvSeriesFinishedUpdating() {
        // empty for framework
    }

    public TvSeriesFinishedUpdating(String title, Boolean finished) {
        this.title = title;
        this.finished = finished;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "TvSeriesFinishedUpdating{" +
                "title='" + title + '\'' +
                ", finished=" + finished +
                '}';
    }
}
