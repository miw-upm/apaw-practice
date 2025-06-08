package es.upm.miw.apaw_practice.domain.models.cinema.composite;

import java.util.List;

public abstract class MovieComponent {
    public void add(MovieComponent component) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    public void remove(MovieComponent component) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    public List<MovieComponent> getChildren() {
        throw new UnsupportedOperationException("Operation not supported");
    }

    public abstract String getTitle();
    public abstract String getDescription();
}