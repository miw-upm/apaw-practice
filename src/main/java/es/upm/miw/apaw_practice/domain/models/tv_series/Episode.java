package es.upm.miw.apaw_practice.domain.models.tv_series;

public class Episode {
    private Integer number;
    private Integer season;
    private Integer duration;

    public Episode() {
        // empty for framework
    }

    public static EpisodeBuilders.Number builder() {
        return new Builder();
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

    public static class Builder implements EpisodeBuilders.Number, EpisodeBuilders.Season, EpisodeBuilders.Duration, EpisodeBuilders.BuildEpisode {
        private final Episode episode;

        public Builder() {
            this.episode = new Episode();
        }


        @Override
        public EpisodeBuilders.Season number(Integer number) {
            this.episode.number = number;
            return this;
        }

        @Override
        public EpisodeBuilders.Duration season(Integer season) {
            this.episode.season = season;
            return this;
        }

        @Override
        public EpisodeBuilders.BuildEpisode duration(Integer duration) {
            this.episode.duration = duration;
            return this;
        }

        @Override
        public Episode build() {
            return this.episode;
        }
    }
}
