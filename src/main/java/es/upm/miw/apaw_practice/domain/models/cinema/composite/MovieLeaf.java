package es.upm.miw.apaw_practice.domain.models.cinema.composite;

import es.upm.miw.apaw_practice.domain.models.cinema.Movie;

public class MovieLeaf extends MovieComponent {
    private final Movie movie;

    public MovieLeaf(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String getTitle() {
        return movie.getTitle();
    }

    @Override
    public String getDescription() {
        return movie.getDescription();
    }
}