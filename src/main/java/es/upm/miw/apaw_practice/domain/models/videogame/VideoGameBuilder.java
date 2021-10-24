package es.upm.miw.apaw_practice.domain.models.videogame;

import java.time.LocalDate;
import java.util.List;

public interface VideoGameBuilder {

    interface Title {
        VideoGameBuilder.Platforms title(String title);
    }

    interface Platforms {
        VideoGameBuilder.Rating platforms(List<Platform> platforms);
    }

    interface Rating {
        VideoGameBuilder.Optionals rating(String rating);
    }

    interface Optionals {

        VideoGameBuilder.Optionals critic(Critic critic);

        VideoGameBuilder.Optionals releaseDate(LocalDate releaseDate);

        VideoGame build();
    }

}
