package es.upm.miw.apaw_practice.domain.models.tv_series;

public interface EpisodeBuilders {
    interface Number {
        Season number(Integer number);
    }
    interface Season {
        Duration season(Integer season);
    }
    interface Duration {
        BuildEpisode duration(Integer duration);
    }
    interface BuildEpisode {
        Episode build();
    }
}